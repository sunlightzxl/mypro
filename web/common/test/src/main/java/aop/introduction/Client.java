package aop.introduction;

import aop.weaving.GreetingFacade;
import aop.weaving.GreetingFacadeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaoxuliang on 16/2/16.
 */
public class Client {

    public static void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-aop.xml");
        GreetingFacadeImpl greetingFacade = (GreetingFacadeImpl) context.getBean("greetingProxy");//转为目标类，而不是接口
        greetingFacade.sayHello("jack");

        ApologyFacade apologyFacade = (ApologyFacade) greetingFacade;//将目标类强制向上转型（引入增强带来的特性）
        apologyFacade.saySorry("jack");
    }

    public static void main(String[] args) {
        test1();
    }
}
