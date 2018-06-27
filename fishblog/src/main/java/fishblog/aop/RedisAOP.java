package fishblog.aop;
import fishblog.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Aspect
@Component
public class RedisAOP{

    RedisAOP(){
        System.out.println("切面初始化。。。");
    }

    @Autowired
    RedisUtil redisUtil;

    @Around(value = "@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint,GetCache log) {
        //先获取目标方法参数
        String applId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0].toString());
        }

        //redis中key格式： applId
        String redisKey = applId;

        //获取从redis中查询到的对象
        Object objectFromRedis = redisUtil.get(redisKey);

        //如果查询到了
        if(null != objectFromRedis) {
            System.out.println(log.name() + ":从redis中查询到了数据...不需要查询数据库");
            return objectFromRedis;
        }

        System.out.println(log.name() + ":没有从redis中查到数据...从数据库中查询");

        //没有查到，那么查询数据库
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //后置：将数据库中查询的数据放到redis中
        System.out.println(log.name() + ":把数据库查询的数据存储到redis中的方法...");

        redisUtil.set(redisKey, object, log.outTime());

        //将查询到的数据返回
        return object;
    }

    //清空缓存，垃圾代码
    @After(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void After(JoinPoint pjp) {
        //得到执行的方法 名
        String str = pjp.getSignature().getName();
        if(str.equals("insertSelective") || str.equals("updateByPrimaryKeySelective") || str.equals("updateStateByPrimaryKey")){
            redisUtil.flushDB();
        }
    }

}
