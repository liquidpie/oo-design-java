package com.vivek.rate.limiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * “Each customer can make X requests per Y seconds”
 * 100 requests for every 10 seconds
 */
public class RateLimiter {

    static Map<Integer, RateLimitCounter> COUNTERS = new HashMap<>();
    static final int CREDIT_MAX_SIZE = 500;
    static final int REQUEST_SIZE_WINDOW = 100;
    static final int WINDOW_SIZE = 100;

    // Perform rate limiting logic for provided customer ID. Return true if the
    // request is allowed, and false if it is not.
    static boolean rateLimit(int customerId) {
        RateLimitCounter counter = COUNTERS.get(customerId);
        if (counter == null) {
            counter = new RateLimitCounter();
            COUNTERS.put(customerId, counter);
        }

        int requestCount = counter.getRequestCount();
        if (requestCount >= REQUEST_SIZE_WINDOW) {
            return false;
        }

        counter.increment();
        return true;
    }

    static class RateLimitCounter {
        Queue<Long> requests;
        long lastRequest;
        int credit;

        public RateLimitCounter() {
            requests = new LinkedList<>();
            credit = 0;
        }

        void increment() {
            if (requests.size() < REQUEST_SIZE_WINDOW) {
                long currentTime = System.currentTimeMillis();
                requests.add(currentTime);
            } else {
                if (credit > 0) {
                    credit--;
                }
            }
        }

        int getRequestCount() {
            if (!requests.isEmpty()) {
                long now = System.currentTimeMillis();
                Long oldestRequest = requests.peek();
                while (oldestRequest != null && (now - oldestRequest > WINDOW_SIZE)) {
                    requests.poll();
                    oldestRequest = requests.peek();
                }
            }

            return requests.size();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        int customerId = 1;
        for (int i = 1; i <= 100; i++) {
            System.out.println(rateLimit(customerId));
        }

        Thread.sleep(1000);

        System.out.println(rateLimit(customerId));
    }

}
