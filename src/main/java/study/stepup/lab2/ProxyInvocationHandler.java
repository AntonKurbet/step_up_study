package study.stepup.lab2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProxyInvocationHandler<T> implements InvocationHandler {

    private final T obj;
    private Map<Method, Object> cache;

    public ProxyInvocationHandler(T obj) {
        this.obj = obj;
        cache = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (cache.get(method) == null) {
                Object result = method.invoke(obj, args);
                cache.put(method, result);
                return result;
            } else {
                return cache.get(method);
            }
        } else if (method.isAnnotationPresent(Mutator.class)) {
            cache.clear();
        } else if (method.isAnnotationPresent(CacheGetter.class)) {
            return Collections.unmodifiableMap(cache);
        }
        return method.invoke(obj, args);
    }
}
