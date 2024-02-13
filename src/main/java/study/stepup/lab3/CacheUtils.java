package study.stepup.lab3;

import java.lang.reflect.Proxy;

public class CacheUtils {

    public static final int SLEEP_TIME = 50;
    private static final CacheStore cacheStore = new CacheStore();
    private static final Thread t = new Thread(new CacheCleaner(cacheStore, SLEEP_TIME));

    public static <T, O> T cache(O obj) {
        ClassLoader objClassLoader = obj.getClass().getClassLoader();
        Class[] interfaces = obj.getClass().getInterfaces();
        T proxyObj = (T) Proxy.newProxyInstance(objClassLoader, interfaces, new ProxyInvocationHandler(obj, cacheStore));
        return proxyObj;
    }

    public static void startCleaner() {
        t.start();
    }
    public static void stopCleaner() {
        t.interrupt();
    }

}
