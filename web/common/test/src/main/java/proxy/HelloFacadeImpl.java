package proxy;

/**
 * Created by zhaoxuliang on 16/2/13.
 */
public class HelloFacadeImpl implements HelloFacade {

    @Override
    public void say(String name) {
        System.out.println("hello! " + name);
    }
}
