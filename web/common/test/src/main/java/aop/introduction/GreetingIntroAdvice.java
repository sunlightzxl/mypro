package aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoxuliang on 16/2/16.
 * 引入增强，在不修改greetingFacadeImpl代码前提下加入功能
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements ApologyFacade {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("sorry! " + name);
    }
}
