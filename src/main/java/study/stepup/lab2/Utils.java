package study.stepup.lab2;

import java.lang.reflect.Proxy;

public class Utils {
    public static <T, O> T cache(O obj) {
        ClassLoader objClassLoader = obj.getClass().getClassLoader();
        Class[] interfaces = obj.getClass().getInterfaces();
        T proxyObj = (T) Proxy.newProxyInstance(objClassLoader, interfaces, new ProxyInvocationHandler(obj));
        return proxyObj;
    }
}
