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
    private Map<String, Object> cache;
    List<String> cached;
    List<String> mutators;

    public ProxyInvocationHandler(T obj) {
        this.obj = obj;
        cache = new HashMap<>();
        cached = Arrays.stream(obj.getClass().getMethods()).filter(m -> m.isAnnotationPresent(Cache.class))
                .map(Method::getName).toList();
        mutators = Arrays.stream(obj.getClass().getMethods()).filter(m -> m.isAnnotationPresent(Mutator.class))
                .map(Method::getName).toList();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (cached.contains(method.getName())) {
            if (cache.get(method.getName()) == null) {
                Object result = method.invoke(obj, args);
                cache.put(method.getName(), result);
                return result;
            } else {
                return cache.get(method.getName());
            }
        } else if (mutators.contains(method.getName())) {
            cache.clear();
        } else if (method.getName().equals("getCache")) {
            return Collections.unmodifiableMap(cache);
        }
        return method.invoke(obj, args);
    }
}
