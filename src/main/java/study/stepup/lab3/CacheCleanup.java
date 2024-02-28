package study.stepup.lab3;

public interface CacheCleanup {
    void callDeferred(Runnable runnable, long delay);
}
