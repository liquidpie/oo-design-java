package com.vivek.rate.limiter;

import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {

    private final long maxBucketSize;
    private final long refillRate;

    private double currentBucketSize;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;

        currentBucketSize = maxBucketSize;
        lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();

        if (currentBucketSize >= tokens) {
            currentBucketSize -= tokens;
            return true;
        }

        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd = (now - lastRefillTimestamp) * refillRate / 1e9;
        currentBucketSize = Math.min(currentBucketSize + tokensToAdd, maxBucketSize);
        lastRefillTimestamp = now;
    }

    public static void main(String[] args) throws InterruptedException {
        // Client can make x requests in y seconds
        // Ex: 10 requests in 2 seconds
        long maxBucketSize = 10;
        long refillRate = 10 / 2;
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(maxBucketSize, refillRate);
        for (int i = 0; i < 11; i++) {
            boolean result = rateLimiter.allowRequest(1);
            System.out.println(result);
        }
        TimeUnit.MILLISECONDS.sleep(600);
        for (int i = 0; i < 11; i++) {
            boolean result = rateLimiter.allowRequest(1);
            System.out.println(result);
        }

    }

}
