package com.example.luckybank.wow;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private ScheduledExecutorService scheduler;
    private HttpRequester requester;
    private final long initialDelay;
    private final long period;

    public Scheduler(long initialDelay, long period) {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.requester = new HttpRequester();
        this.initialDelay = initialDelay;
        this.period = period;

        // Запускаем задачу с начальной задержкой и затем выполняем её с периодичностью
        scheduler.scheduleAtFixedRate(requester::sendRequest, initialDelay, period, TimeUnit.HOURS);
    }

    public void shutdown() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
