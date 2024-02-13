package study.stepup.lab3;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
class CacheResult {
    private long ttl;
    private Object value;

    public void addTtl(long ttl) {
        this.ttl = System.currentTimeMillis() + ttl;
    }
}

public class CacheStore {
    private final Map<String, CacheResult> cache = new HashMap<>();

    public void put(String key, Object value, long ttl) {
        cache.put(key, new CacheResult(System.currentTimeMillis() + ttl, value));
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public Object get(String key, long ttl) {
        if (cache.get(key) != null) {
            cache.get(key).addTtl(ttl);
            return cache.get(key).getValue();
        }
        return null;
    }

    public void clear() {
        cache.clear();
    }

    public Map<String, CacheResult> info() {
        return Collections.unmodifiableMap(cache);
    }
}
