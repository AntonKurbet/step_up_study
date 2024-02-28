package study.stepup.lab3;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCleanup implements CacheCleanup {

    final ScheduledExecutorService executorService;

    public ThreadPoolCleanup(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void callDeferred(Runnable runnable, long delay) {
        executorService.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }
}
