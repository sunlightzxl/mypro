package aop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by zhaoxuliang on 16/2/15.
 */
public class Client {

    public static void test1(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingFacadeImpl());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        GreetingFacade greetingFacade = (GreetingFacade) proxyFactory.getProxy();

        greetingFacade.sayHello("jack");
    }

    public static void test2(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingFacadeImpl());
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());

        GreetingFacade greetingFacade = (GreetingFacade) proxyFactory.getProxy();

        greetingFacade.sayHello("jack");
    }


    public static void main(String[] args) {
        test2();
    }
}
