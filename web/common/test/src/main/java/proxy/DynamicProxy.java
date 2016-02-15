package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhaoxuliang on 16/2/13.
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void test1() {
        HelloFacade helloFacade = new HelloFacadeImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(helloFacade);

        HelloFacade helloProxy = (HelloFacade) Proxy.newProxyInstance(helloFacade.getClass().getClassLoader(), helloFacade.getClass().getInterfaces(), dynamicProxy);

        helloProxy.say("jack");
    }

    public static void test2() {
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloFacadeImpl());
        HelloFacade helloProxy = dynamicProxy.getProxy();
        helloProxy.say("Jack");
    }


    public static void main(String[] args) {
        test2();
    }
}
