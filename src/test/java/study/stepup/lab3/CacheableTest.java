package study.stepup.lab3;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheableTest {
    Account account;
    CachedAccount cachedAccount;

    @BeforeEach
    void init(){
        account = new Account("RUB", 100);
        cachedAccount = CacheUtils.cache(account);
    }

    @Test
    @SneakyThrows
    void testCachedGet() {
        cachedAccount.getCurrencyWithSum();
        assertFalse(cachedAccount.getCache().isEmpty());
    }

    @Test
    @SneakyThrows
    void testCachedGet2() {
        cachedAccount.isCurrency("USD");
        assertFalse(cachedAccount.getCache().isEmpty());
    }

    @Test
    @SneakyThrows
    void testCacheClear() {
        cachedAccount.getCurrencyWithSum();
        Thread.sleep(1000);
        assertTrue(cachedAccount.getCache().isEmpty());
    }

    @Test
    @SneakyThrows
    void testCacheClear2() {
        cachedAccount.isCurrency("USD");
        Thread.sleep(1000);
        assertTrue(cachedAccount.getCache().isEmpty());
    }

    @Test
    @SneakyThrows
    void testCacheLongLife() {
        cachedAccount.getCurrencyWithSum();
        Thread.sleep(800);
        cachedAccount.getCurrencyWithSum();
        assertFalse(cachedAccount.getCache().isEmpty());
    }
}
