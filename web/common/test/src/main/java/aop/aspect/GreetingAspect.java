package aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoxuliang on 16/2/16.
 * 基于注解拦截
 */
@Aspect
@Component
public class GreetingAspect {

    @Around("execution(* aop.weaving.GreetingFacadeImpl.* (..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        before();
        Object result = point.proceed();
        after();
        return result;
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }


}
