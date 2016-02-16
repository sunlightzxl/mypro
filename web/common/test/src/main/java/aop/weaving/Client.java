package aop.weaving;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaoxuliang on 16/2/15.
 */
public class Client {

    public static void test1() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingFacadeImpl());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());

        GreetingFacade greetingFacade = (GreetingFacade) proxyFactory.getProxy();

        greetingFacade.sayHello("jack");
    }

    public static void test2() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingFacadeImpl());
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());

        GreetingFacade greetingFacade = (GreetingFacade) proxyFactory.getProxy();

        greetingFacade.sayHello("jack");
    }

    public static void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-aop.xml");
        GreetingFacade greetingFacade = (GreetingFacade) context.getBean("greetingProxy");
        greetingFacade.sayHello("jack");
    }

    public static void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-aop.xml");
        GreetingFacadeImpl greetingFacade = (GreetingFacadeImpl) context.getBean("greetingProxy");
        greetingFacade.goodMorning("jack");
    }

    public static void test5(){
        //将spring自动动态代理激活测试
        //test3();
        //test4();
    }


    public static void main(String[] args) {
        test3();
    }
}
