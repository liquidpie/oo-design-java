package com.vivek.counter;

public class ArrayBasedCounter implements HitCounter {

    private final int[] hits;
    private final int[] timestamps;
    private final int WINDOW = 300;

    public ArrayBasedCounter() {
        hits = new int[WINDOW];
        timestamps = new int[WINDOW];
    }

    @Override
    public void hit(int timestamp) {
        int index = timestamp % WINDOW;
        if (timestamps[index] != timestamp) {
            timestamps[index] = timestamp;
            hits[index] = 1;
        } else {
            ++hits[index];
        }
    }

    @Override
    public int getHits(int timestamp) {
        int result = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - timestamps[i] < 300) {
                result += hits[i];
            }
        }

        return result;
    }
}
