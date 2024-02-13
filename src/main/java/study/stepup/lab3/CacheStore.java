package study.stepup.lab3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Getter
class CacheResult {
    private long ttl;
    private Object value;

    public void addTtl(long ttl) {
        this.ttl = System.currentTimeMillis() + ttl;
    }
    @Override
    public String toString() {
       return value.toString() + ":" + ttl;
    }
}

@Log
public class CacheStore {
    private final Map<String, CacheResult> cache = new ConcurrentHashMap<>();

    synchronized public void put(String key, Object value, long ttl) {
        cache.put(key, new CacheResult(System.currentTimeMillis() + ttl, value));
        log.info(cache.toString());
    }

    synchronized public void remove(String key) {
        cache.remove(key);
        log.info(cache.toString());
    }

    synchronized public Object get(String key, long ttl) {
        if (cache.get(key) == null) return null;
        cache.get(key).addTtl(ttl);
        log.info(cache.get(key).toString());
        return cache.get(key).getValue();
    }

    synchronized public void clear() {
        cache.clear();
        log.info(cache.toString());
    }

    synchronized public Map<String, CacheResult> info() {
        return Collections.unmodifiableMap(cache);
    }
}
