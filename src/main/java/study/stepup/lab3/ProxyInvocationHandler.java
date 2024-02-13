package study.stepup.lab3;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@AllArgsConstructor
@Log
public class ProxyInvocationHandler<T> implements InvocationHandler {

    private final T obj;
    private final CacheStore cache;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String hash = String.format("%d-%d-%d", obj.hashCode(), method.hashCode(), Arrays.hashCode(args));
        if (method.isAnnotationPresent(Cache.class)) {
            var ttl = method.getAnnotation(Cache.class).value();
            var cacheValue = cache.get(hash, ttl);
            if (cacheValue == null) {
                Object result = method.invoke(obj, args);
                cache.put(hash, result, ttl);
                log.info("put:" + result.toString());
                return result;
            } else {
                log.info("get:" + cacheValue);
                return cacheValue;
            }
        } else if (method.isAnnotationPresent(Mutator.class)) {
            log.info("clear cache");
            cache.clear();
        } else if (method.isAnnotationPresent(CacheGetter.class)) {
            return cache.info();
        }
        return method.invoke(obj, args);
    }

}
