package study.stepup.lab3;

public class CacheCleaner implements Runnable {
    private final CacheStore cacheStore;
    private final long sleepTime;
    public CacheCleaner(CacheStore cacheStore, long sleepTime) {
        this.cacheStore = cacheStore;
        this.sleepTime = sleepTime;
    }

    public void run() {
        for(var entry: cacheStore.info().entrySet()) {
            if (entry.getValue().getTtl() < System.currentTimeMillis()) {
                cacheStore.remove(entry.getKey());
            }
        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
