package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * Created by zhaoxuliang on 16/2/15.
 * 注意这个接口是org.aopalliance.intercept.MethodInterceptor
 * 非spring提供
 */
public class GreetingAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object result = methodInvocation.proceed();
        after();
        return result;
    }

    public void after() {
        System.out.println("before");
    }

    public void before() {
        System.out.println("after");
    }
}
