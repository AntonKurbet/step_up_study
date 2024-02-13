package study.stepup.lab3;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    static void start() {
        CacheUtils.startCleaner();
    }
    @AfterAll
    static void stop() {
        CacheUtils.stopCleaner();
    }

    @Test
    void testCachedGet() {
        cachedAccount.getCurrencyWithSum();
        assertFalse(cachedAccount.getCache().isEmpty());
    }

    @Test
    void testCachedGet2() {
        cachedAccount.isCurrency("USD");
        assertFalse(cachedAccount.getCache().isEmpty());
    }

    @Test
    @SneakyThrows
    void testCacheClear() {
        cachedAccount.getCurrencyWithSum();
        Thread.sleep(1200);
        assertTrue(cachedAccount.getCache().isEmpty());
    }

    @Test
    @SneakyThrows
    void testCacheClear2() {
        cachedAccount.isCurrency("USD");
        Thread.sleep(1200);
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

    @Test
    @SneakyThrows
    void concurrentTest() {
        var account2 = new Account("USD", 100);
        CachedAccount cachedAccount2 = CacheUtils.cache(account2);
        cachedAccount.getCurrencyWithSum();
        cachedAccount2.getCurrencyWithSum();
        cachedAccount.getCurrencyWithSum();
        cachedAccount2.getCurrencyWithSum();
        Thread.sleep(1000);
        cachedAccount.getCurrencyWithSum();
        cachedAccount2.getCurrencyWithSum();
        Thread.sleep(800);
        cachedAccount.getCurrencyWithSum();
        cachedAccount2.getCurrencyWithSum();
    }
}
