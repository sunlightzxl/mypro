package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhaoxuliang on 16/2/13.
 * 代理没有任何接口的类
 * user:spring hibernate
 */
public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance = new CGLibProxy();

    public static CGLibProxy getInstance() {
        return instance;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

    public static void test1() {
        CGLibProxy cgLibProxy = new CGLibProxy();
        HelloFacade helloProxy = cgLibProxy.getProxy(HelloFacadeImpl.class);
        helloProxy.say("jack");
    }

    public static void test2() {
        HelloFacade helloProxy = CGLibProxy.getInstance().getProxy(HelloFacadeImpl.class);
        helloProxy.say("jack");
    }

    /**
     * 代理没有接口的类，OK
     */
    public static void test3(){
        HelloNoFacadeImpl helloProxy = CGLibProxy.getInstance().getProxy(HelloNoFacadeImpl.class);
        helloProxy.say("jack");
    }

    public static void main(String[] args) {
        test3();
    }

}
