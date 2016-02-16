package aop;

import org.springframework.stereotype.Component;

/**
 * Created by zhaoxuliang on 16/2/15.
 */
@Component
public class GreetingFacadeImpl implements GreetingFacade {
    @Override
    public void sayHello(String name) {
//        before();
        System.out.println("hello! " + name);
//        after();
        throw new RuntimeException("Error");
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
