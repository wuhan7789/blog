import fishblog.pojo.vo.ArticleVo;
import fishblog.service.In.ArticleIn;
import fishblog.util.PageList;
import fishblog.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-redis.xml"})
public class TestClass {

    @Autowired
    ArticleIn articleIn;
    @Autowired
    RedisUtil redisUtil;

    //注意这个工具类的bean在另一个配置文件里  error :配置重复会有死循环
    /* ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
    RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");*/

    @Test
    public void test() throws Exception {
        PageList<ArticleVo> p = articleIn.selectByExample(null,true);
        redisUtil.del("p");
        redisUtil.set("p",p);
        System.out.println(redisUtil.get("p"));
    }


//redis-cli -h 118.24.158.130 -p 6379 -a wuhan7789
    @Test
    public void test1() throws Exception {
        //创建一个jedis的对象。
        Jedis jedis = new Jedis("118.24.158.130", 6379);
        //权限认证
        jedis.auth("wuhan7789");
        jedis.del("key1");
        //调用jedis对象的方法，方法名称和redis的命令一致。
        jedis.set("key1", "jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis。
        jedis.close();
    }

    @Test
    public void test3() throws Exception {

    }

}
