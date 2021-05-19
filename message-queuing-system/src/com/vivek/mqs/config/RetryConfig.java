package com.vivek.mqs.config;

import java.util.Objects;

public class RetryConfig {

    public enum RetryPolicy {
        DELAYED_RETRY,
        ENQUEUE_MESSAGE,
        NONE
    }

    private static final int DEFAULT_DELAY_MS = 1000;

    private final int maxAttempts;
    private final RetryPolicy retryPolicy;
    private final int delayMs;

    public RetryConfig(Builder builder) {
        this.maxAttempts = builder.maxAttempts;
        this.retryPolicy = builder.retryPolicy;
        this.delayMs = Objects.nonNull(builder.delayMs) ? builder.delayMs : DEFAULT_DELAY_MS;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public int getDelayMs() {
        return delayMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetryConfig that = (RetryConfig) o;
        return maxAttempts == that.maxAttempts && delayMs == that.delayMs && retryPolicy == that.retryPolicy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxAttempts, retryPolicy, delayMs);
    }

    @Override
    public String toString() {
        return "RetryConfig{" +
                "maxAttempts=" + maxAttempts +
                ", retryPolicy=" + retryPolicy +
                ", delayMs=" + delayMs +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int maxAttempts;
        private RetryPolicy retryPolicy;
        private Integer delayMs;

        public Builder withMaxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return this;
        }

        public Builder withRetryPolicy(RetryPolicy retryPolicy) {
            this.retryPolicy = retryPolicy;
            return this;
        }

        public Builder withDelayMs(Integer delayMs) {
            this.delayMs = delayMs;
            return this;
        }

        public RetryConfig build() {
            return new RetryConfig(this);
        }

    }
}
