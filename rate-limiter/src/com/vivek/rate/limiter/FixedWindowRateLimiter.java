package com.vivek.rate.limiter;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Fixed Window Algorithm:
 * The Fixed Window Algorithm is the simplest rate limiting algorithm.
 * It limits the rate by counting the number of requests made within a fixed time window.
 * If the number of requests exceeds the limit, then any subsequent requests are blocked until the start of the next window.
 * For example, if the limit is set to 10 requests per second,
 * then the algorithm counts the number of requests made in the last second and blocks any additional requests until the start of the next second.
 *
 * In the below code, we have a FixedWindowRateLimiter class that implements the Fixed Window Algorithm for rate limiting.
 * It takes the maximum number of requests allowed within a time window (limit) and the duration of the time window in seconds (windowSeconds)
 * as parameters in the constructor.
 *
 * The allowRequest() method is used to check if a request is allowed or not. It synchronizes the method to ensure thread safety.
 *
 * The clearExpiredRequests() method is called before checking the request queue for the number of requests.
 * It clears any expired requests from the queue based on the current time and the window duration.
 * The expired requests are removed from the queue to keep track of only the requests within the defined time window.
 *
 * You can create an instance of FixedWindowRateLimiter and use the allowRequest() method to control the rate
 * at which requests are allowed within the fixed window.
 *
 * It’s important to note that the Fixed Window Algorithm resets the request count at the start of each time window.
 * This means that the rate limit is enforced strictly within each window,
 * but it doesn’t take into account the distribution of requests within the window.
 */
public class FixedWindowRateLimiter {

    private final int limit; // Maximum number of requests allowed within the time window
    private final int windowSeconds; // Duration of the time window in seconds
    private final Queue<Instant> requestQueue; // Queue to store request timestamps

    public FixedWindowRateLimiter(int limit, int windowSeconds) {
        this.limit = limit;
        this.windowSeconds = windowSeconds;
        this.requestQueue = new ArrayDeque<>();
    }

    public synchronized boolean allowRequest() {
        clearExpiredRequests(); // Clear expired requests from the queue

        if (requestQueue.size() < limit) {
            requestQueue.offer(Instant.now()); // Add current request timestamp to the queue
            return true; // Request is allowed
        } else {
            return false; // Request is not allowed
        }
    }

    private void clearExpiredRequests() {
        Instant now = Instant.now();
        Instant windowStart = now.minusSeconds(windowSeconds);

        while (!requestQueue.isEmpty() && requestQueue.peek().isBefore(windowStart)) {
            requestQueue.poll(); // Remove expired requests from the queue
        }
    }

}
