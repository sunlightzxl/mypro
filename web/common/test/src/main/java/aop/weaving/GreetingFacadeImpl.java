package aop.weaving;

import org.springframework.stereotype.Component;

/**
 * Created by zhaoxuliang on 16/2/15.
 */
@Component
public class GreetingFacadeImpl implements GreetingFacade {
    @Override
    public void sayHello(String name) {
        System.out.println("hello! " + name);
//        throw new RuntimeException("Error");
    }

    public void goodMorning(String name) {
        System.out.println("good morning! " + name);
    }

    public void goodNight(String name) {
        System.out.println("good night! " + name);
    }

}
