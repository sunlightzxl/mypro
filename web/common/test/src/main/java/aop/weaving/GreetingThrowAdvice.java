package aop.weaving;


import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhaoxuliang on 16/2/16.
 */
@Component
public class GreetingThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("------Throw Exception------");
        System.out.println("Target Class: " + target.getClass().getName());
        System.out.println("Method Name: " + method.getName());
        System.out.println("Exception Message: " + e.getMessage());
        System.out.println("---------------------------");
    }
}
