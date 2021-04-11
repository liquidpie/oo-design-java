package com.vivek.counter;

public interface HitCounter {

    void hit(int timestamp);

    int getHits(int timestamp);

}
