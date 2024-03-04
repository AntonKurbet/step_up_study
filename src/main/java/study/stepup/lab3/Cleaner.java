package study.stepup.lab3;

import java.util.ArrayList;
import java.util.List;

public class Cleaner implements CacheCleanup{
    List<Runnable> queue = new ArrayList<>();

    @Override
    public void callDeferred(Runnable runnable, long delay) {
        queue.add(runnable);
    }

    public void flush() {
        queue.forEach(Runnable::run);
    }
}
