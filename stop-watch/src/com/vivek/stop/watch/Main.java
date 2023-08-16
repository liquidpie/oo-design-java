package com.vivek.stop.watch;

/**
 * Design Object Oriented class structure for a stop watch similar to the one that appears on a phone.
 *
 * It should include features for the stopwatch, pause, cancel, reset and laps.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(2000);
        stopWatch.pause();
        Thread.sleep(1000);
        stopWatch.resumeWatch();
        Thread.sleep(1000);
        stopWatch.lap();
        Thread.sleep(1000);
        stopWatch.lap();
        Thread.sleep(1000);
        stopWatch.lap();
        Thread.sleep(2500);
        stopWatch.pause();
        stopWatch.stop();
    }

}