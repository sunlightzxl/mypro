package aop;

/**
 * Created by zhaoxuliang on 16/2/15.
 */
public class GreetingFacadeImpl implements GreetingFacade {
    @Override
    public void sayHello(String name) {
//        before();
        System.out.println("hello! " + name);
//        after();
    }

    @Deprecated
    private void before() {
        System.out.println("before");
    }

    @Deprecated
    private void after() {
        System.out.println("after");
    }
}
