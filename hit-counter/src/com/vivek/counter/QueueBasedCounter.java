package com.vivek.counter;

import java.util.LinkedList;
import java.util.Queue;

public class QueueBasedCounter implements HitCounter {

    private final Queue<Integer> queue = new LinkedList<>();

    @Override
    public void hit(int timestamp) {
        queue.add(timestamp);
    }

    @Override
    public int getHits(int timestamp) {
        int WINDOW = 300;
        while (!queue.isEmpty() && queue.peek() - timestamp > WINDOW) {
            queue.poll();
        }

        return queue.size();
    }
}
