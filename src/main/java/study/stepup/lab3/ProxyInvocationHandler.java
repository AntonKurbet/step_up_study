package study.stepup.lab3;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@AllArgsConstructor
public class ProxyInvocationHandler<T> implements InvocationHandler {

    private final T obj;
    private final CacheStore cache;

    public static final int SLEEP_TIME = 500;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Thread t = new Thread(new CacheCleaner(cache, SLEEP_TIME));
        t.start();

        String hash = String.format("%d-%d-%d", obj.hashCode(), method.hashCode(), Arrays.hashCode(args));
        if (method.isAnnotationPresent(Cache.class)) {
            var ttl =  method.getAnnotation(Cache.class).value();
            var cacheValue = cache.get(hash,ttl);
            if (cacheValue == null) {
                Object result = method.invoke(obj, args);
                cache.put(hash, result, ttl);
                return result;
            } else {
                return cacheValue;
            }
        } else if (method.isAnnotationPresent(Mutator.class)) {
            cache.clear();
        } else if (method.isAnnotationPresent(CacheGetter.class)) {
            return cache.info();
        }
        return method.invoke(obj, args);
    }

}
