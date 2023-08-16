package com.vivek.stop.watch;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class StopWatch extends Thread {

    private long elapsed;
    private boolean running;
    private Instant start;
    private Instant end;
    private Instant lastLap;

    public StopWatch() {
        elapsed = 0;
        running = false;
    }

    public void reset() {
        elapsed = 0;
        running = false;
    }

    public void startWatch() {
        elapsed = 0;
        start = Instant.now();
        lastLap = start;
        running = true;
    }

    public void lap() {
        if (running) {
            Instant now = Instant.now();
            Duration time = Duration.between(lastLap, now);
            lastLap = now;
            System.out.printf("Lap Duration: %d milli seconds\n", time.toMillis());
        } else {
            System.out.println("Watch already paused!");
        }
    }

    public void pause() {
        if (running) {
            end = Instant.now();
            elapsed += Duration.between(start, end).toMillis();
            running = false;
            System.out.printf("PAUSED! Total Time %d milli seconds\n", elapsed);
        } else {
            System.out.println("Watch already paused!");
        }
    }

    public void resumeWatch() {
        if (!running) {
            System.out.println("Resuming watch!");
            start = Instant.now();
            lastLap = start;
            running = true;
        } else {
            System.out.println("Watch already paused!");
        }
    }

    public void printTime() {
        if (running) {
            long _elapsed = Duration.between(start, Instant.now()).toMillis();
            long totalTime = elapsed + _elapsed;
            System.out.printf("Time: %d milli seconds\n", totalTime);
        }
    }

    public void run() {
        startWatch();
        while (true) {
            if (running)
                printTime();

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();;
            }
        }
    }
}
