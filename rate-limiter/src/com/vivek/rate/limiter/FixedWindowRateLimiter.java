package com.vivek.rate.limiter;

import java.util.HashMap;
import java.util.Map;

/**
 * Fixed Window Algorithm:
 *
 * Fixed Window Rate Limiting works by dividing time into fixed windows and allowing a certain number of requests in each window.
 * For example, if we have a limit of 100 requests per hour, and our window starts at 2:00, we can make 100 requests between 2:00 and 3:00.
 *
 * However, if we make all 100 requests at 2:10, we will have to wait until 3:00 to make another request.
 * This can lead to uneven distribution of requests, with potential for bursts of traffic at the start of each window.
 *
 * https://www.codereliant.io/rate-limiting-deep-dive/
 */
public class FixedWindowRateLimiter {

    private final int maxRequestPerWindow;
    private final long windowSizeInMillis;
    private final Map<String, Window> store;

    public FixedWindowRateLimiter(int maxRequestPerWindow, long windowSizeInMillis) {
        this.maxRequestPerWindow = maxRequestPerWindow;
        this.windowSizeInMillis = windowSizeInMillis;
        this.store = new HashMap<>();
    }

    public synchronized boolean isAllowed(String clientId) {
        long currentTimeMillis = System.currentTimeMillis();
        Window window = store.get(clientId);

        // If the window doesn't exist or has expired, create a new window
        if (window == null || window.getStartTime() < currentTimeMillis - windowSizeInMillis) {
            window = new Window(currentTimeMillis, 0);
        }

        // Check if the number of requests in the window exceeds the maximum allowed
        if (window.getRequestCount() >= maxRequestPerWindow) {
            return false; // Request is not allowed
        }

        // Increment the request count and update the window in the store
        window.setRequestCount(window.getRequestCount() + 1);
        store.put(clientId, window);
        return true; // Request is allowed
    }

    private static class Window {
        private final long startTime;
        private int requestCount;

        public Window(long startTime, int requestCount) {
            this.startTime = startTime;
            this.requestCount = requestCount;
        }

        public long getStartTime() {
            return startTime;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }
    }

}
