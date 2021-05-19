package com.vivek.mqs.config;

import com.vivek.mqs.domain.Message;

import java.util.function.Consumer;

public class SubscriberConfig {

    private final Consumer<Message> callback;
    private final Integer fetchSize;

    private SubscriberConfig(Builder builder) {
        this.callback = builder.callback;
        this.fetchSize = builder.fetchSize;
    }

    public Consumer<Message> getCallback() {
        return callback;
    }

    public Integer getFetchSize() {
        return fetchSize;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Consumer<Message> callback;
        private Integer fetchSize;

        public Builder withCallback(Consumer<Message> callback) {
            this.callback = callback;
            return this;
        }

        public Builder withFetchSize(Integer fetchSize) {
            this.fetchSize = fetchSize;
            return this;
        }

        public SubscriberConfig build() {
            return new SubscriberConfig(this);
        }

    }
}
