package study.stepup.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheableTest {
    Account account;
    CachedAccount cachedAccount;

    @BeforeEach
    void init(){
        account = new Account("RUB", 100);
        cachedAccount = Utils.cache(account);
    }
    @Test
    void testNonCached() {
        assertTrue(cachedAccount.getCache().isEmpty());
    }

    @Test
    void testCached() {
        cachedAccount.getCurrencyWithSum();
        assertFalse(cachedAccount.getCache().isEmpty());
    }

    @Test
    void testNonCachedValue() {
        var result = cachedAccount.getCurrencyWithSum();
        assertEquals("100 RUB", result);
    }

    @Test
    void testCachedValue() {
        var result = cachedAccount.getCurrencyWithSum();
        result = cachedAccount.getCurrencyWithSum();
        assertEquals("100 RUB", result);
    }

    @Test
    void testCachedFlush1() {
        var result = cachedAccount.getCurrencyWithSum();
        cachedAccount.setCurrency("EUR");
        assertTrue(cachedAccount.getCache().isEmpty());
    }

    @Test
    void testCachedFlush2() {
        var result = cachedAccount.getCurrencyWithSum();
        cachedAccount.setSum(200);
        assertTrue(cachedAccount.getCache().isEmpty());
    }
}
