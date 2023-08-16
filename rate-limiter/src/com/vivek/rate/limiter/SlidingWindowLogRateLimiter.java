package com.vivek.rate.limiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * The Sliding Window algorithm improves upon the Fixed Window algorithm by providing a more evenly distributed control of requests.
 * Instead of allowing the entire limit of requests at the start of the window, the Sliding Window algorithm distributes the allowance throughout the window.
 *
 * The Sliding Window algorithm works by keeping track of the timestamp of each request in a rolling time window.
 * The server maintains a count of requests for each client and the timestamp of their oldest request.
 * When a new request comes in, the server checks the count of requests in the sliding window.
 * If the count exceeds the limit, the server denies the request.
 */
public class SlidingWindowLogRateLimiter {

    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, Deque<Long>> timestamps;

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.timestamps = new ConcurrentHashMap<>();
    }

    public boolean isAllowed(String clientId) {
        // Get the timestamp deque for the client, creating a new one if it doesn't exist
        Deque<Long> clientTimestamps = timestamps.computeIfAbsent(clientId, k -> new ConcurrentLinkedDeque<>());

        // Get the current timestamp in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Remove timestamps older than the sliding window
        while (!clientTimestamps.isEmpty() && currentTimeMillis - clientTimestamps.peekFirst() > windowSizeInMillis) {
            clientTimestamps.pollFirst();
        }

        // Check if the number of requests in the sliding window exceeds the maximum allowed
        if (clientTimestamps.size() < maxRequests) {
            clientTimestamps.addLast(currentTimeMillis);
            return true; // Request is allowed
        }
        return false; // Request is not allowed
    }
}
