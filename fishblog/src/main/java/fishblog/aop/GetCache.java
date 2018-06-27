package fishblog.aop;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解，对打上这个注解的类进行切面redis缓存
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface GetCache {

  //过期时间 默认5分钟
  long outTime() default 300;

  String name() default "";

  String value() default "";


}