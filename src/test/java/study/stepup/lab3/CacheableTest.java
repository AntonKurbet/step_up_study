package study.stepup.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CacheableTest {
    @Test
    void testCleanQueued() {
        var cleanup = new Cleaner();
        CachedAccount account = CacheUtils.<Account,CachedAccount>cache(new Account("RUB", 100), cleanup);

        var v1 = account.getCurrencyWithSum();
        assertEquals(cleanup.queue.size(),1);
        var v2 = account.getCurrencyWithSum();
        assertEquals(v1,v2);

        cleanup.flush();
        assertNotEquals(cleanup.queue.size(),0);
    }
}
