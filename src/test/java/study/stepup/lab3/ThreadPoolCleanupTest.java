package study.stepup.lab3;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCleanupTest {
    @Test
    void scheduleTest() {
        var executor = Mockito.mock(ScheduledExecutorService.class);
        var task = (Runnable)()->{};
        new ThreadPoolCleanup(executor).callDeferred(task,1000);
        Mockito.verify(executor).schedule(task,1000L, TimeUnit.MILLISECONDS);
    }
}
