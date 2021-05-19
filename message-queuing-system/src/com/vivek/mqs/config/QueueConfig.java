package com.vivek.mqs.config;

import com.vivek.mqs.domain.QueueType;

import java.util.Objects;

public class QueueConfig {

    private final String name;
    private final QueueType type;
    private final Integer sizeLimit;
    private final RetryConfig retryConfig;

    private QueueConfig(Builder builder) {
        this.name = builder.name;
        this.type = builder.type != null ? builder.type : QueueType.FIFO;
        this.sizeLimit = builder.sizeLimit;
        this.retryConfig = builder.retryConfig;
    }

    public String getName() {
        return name;
    }

    public QueueType getType() {
        return type;
    }

    public Integer getSizeLimit() {
        return sizeLimit;
    }

    public RetryConfig getRetryConfig() {
        return retryConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueConfig that = (QueueConfig) o;
        return Objects.equals(name, that.name) && type == that.type && Objects.equals(sizeLimit, that.sizeLimit) && Objects.equals(retryConfig, that.retryConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, sizeLimit, retryConfig);
    }

    @Override
    public String toString() {
        return "QueueConfig{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", sizeLimit=" + sizeLimit +
                ", retryConfig=" + retryConfig +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private QueueType type;
        private Integer sizeLimit;
        private RetryConfig retryConfig;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(QueueType type) {
            this.type = type;
            return this;
        }

        public Builder withSizeLimit(Integer sizeLimit) {
            this.sizeLimit = sizeLimit;
            return this;
        }

        public Builder withRetryConfig(RetryConfig retryConfig) {
            this.retryConfig = retryConfig;
            return this;
        }

        public QueueConfig build() {
            return new QueueConfig(this);
        }

    }

}
