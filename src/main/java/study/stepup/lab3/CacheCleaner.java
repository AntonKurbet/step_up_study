package study.stepup.lab3;

import lombok.extern.java.Log;

@Log
public class CacheCleaner implements Runnable {
    private final CacheStore cacheStore;
    private final long sleepTime;
    private volatile boolean alive = true;

    public CacheCleaner(CacheStore cacheStore, long sleepTime) {
        this.cacheStore = cacheStore;
        this.sleepTime = sleepTime;
    }

    public void run() {
        log.info("run cache cleaner");
        while (alive) {
            for (var entry : cacheStore.info().entrySet()) {
                if (entry.getValue().getTtl() < System.currentTimeMillis()) {
                    log.info("remove:" + entry.getKey());
                    cacheStore.remove(entry.getKey());
                }
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                alive = false;
                log.info("stop cache cleaner");
            }
        }
    }

}
