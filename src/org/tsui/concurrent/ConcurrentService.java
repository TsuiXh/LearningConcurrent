package org.tsui.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentService {

    private static final int NTHREAD = 10;

    private AtomicInteger currentTimes = new AtomicInteger(0);
    private AtomicInteger totalTimes = new AtomicInteger(0);

    private Integer calPercentage() {
        return (currentTimes.get() / totalTimes.get()) * 100;
    }

    public void start(int times) {
        totalTimes.getAndSet(times);
        ExecutorService executor = Executors.newFixedThreadPool(NTHREAD);
        for (int i = 0; i < times; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(200);
                    currentTimes.getAndIncrement();
                    System.out.println(currentTimes.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    public int getPercentage() {
        return calPercentage();
    }
}
