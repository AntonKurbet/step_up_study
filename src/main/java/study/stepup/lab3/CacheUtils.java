package study.stepup.lab3;

import java.lang.reflect.Proxy;

public class CacheUtils {

    public static <T, O> T cache(O obj, CacheCleanup cacheCleanup) {
        ClassLoader objClassLoader = obj.getClass().getClassLoader();
        Class[] interfaces = obj.getClass().getInterfaces();
        T proxyObj = (T) Proxy.newProxyInstance(objClassLoader, interfaces, new CacheInvocationHandler(obj, cacheCleanup));
        return proxyObj;
    }

}
