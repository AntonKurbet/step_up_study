package study.stepup.lab3;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Log
@AllArgsConstructor
public class CacheInvocationHandler<T> implements InvocationHandler {

    private final T obj;
    private final CacheCleanup cacheCleanup;
    private final ConcurrentHashMap<String,Object> store = new ConcurrentHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String hash = String.format("%d-%d-%d", obj.hashCode(), method.hashCode(), Arrays.hashCode(args));
        if (method.isAnnotationPresent(Cache.class)) {
            var ttl = method.getAnnotation(Cache.class).value();
            var cacheValue = store.get(hash);
            if (cacheValue == null) {
                Object result = method.invoke(obj, args);
                store.put(hash, result);
                cacheCleanup.callDeferred(()->store.remove(hash), ttl);
                log.info("put:" + result.toString());
                return result;
            } else {
                log.info("get:" + cacheValue);
                return cacheValue;
            }
        }
        return method.invoke(obj, args);
    }

}
