##springboot的自动装配
何为springboot的自动装配
我们先看一个例子
先创建一个工程名为 iron-study-autoconfigure-self 
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>2.1.9.RELEASE</version>
        </dependency>
        导入依赖
        
 创建一个springboot的main函数 运行
 @SpringBootApplication
 public class Applicaiton {
 
     public static void main(String[] args) {
 
         final ConfigurableApplicationContext context = SpringApplication.run(Applicaiton.class);
         final Object user = context.getBean("redisTemplate");
         System.out.println(user);
     }
 }
 获取redisTemplate的bean 此时 就会出现
 Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'redisTemplate' available
 	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:685)
 	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1218)
 	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:291)
 	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
 	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1087)
 	at com.study.Applicaiton.main(Applicaiton.java:21)
 	
 如果这时候  我们加入了一个
      <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-data-redis</artifactId>
             <version>2.1.9.RELEASE</version>
         </dependency>
         redis的starter 时候 再次运行main函数
         
org.springframework.data.redis.core.RedisTemplate@77b7ffa4

就会发现我们的spring容器里面有一个redisTemplate的bean.
此时我们发现，在我们的项目中没有初始化任何一个redisTemplate的bean，但是在spring容器中久存在一个，
这就是springboot的自动装配。在引入一个spring-data-redis的jiar时候  springboot将一个redisTemplate自动注入了一IOC容器中。

我们如何实现一个starter
首先我们需要学习一下几个注解
@Condition
@Enable*
@Import

@Condition
首选创建一个项目 名字叫做 iron-study-autoconfigure-other
在该项目下面创建一个User类
public class User {
}
在创建一个Config类,在config类中实例化创建一个User的bean
@Configuration
public class Config {
    @Bean
    public User user(){
        return new User();
    }
}
在将iron-study-autoconfigure-other导入到iron-study-autoconfigure-self中
   final Object user = context.getBean("redisTemplate");将这句代码改成
      final Object user = context.getBean("user");

我们发现是可以获取到这个User对象的
User@7dac3fd8
说明此时的User对象已经被自动装配到spring容器中了
那么此时有一个需求 如果我们导入了jedis的jar包 就能


