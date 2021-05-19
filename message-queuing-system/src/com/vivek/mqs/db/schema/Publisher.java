package com.vivek.mqs.db.schema;

public class Publisher {

    private final String id;
    private String queueName;
    private Long createdAt;
    private Long modifiedAt;
    private boolean deleted;

    public Publisher(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getQueueName() {
        return queueName;
    }

    public Publisher setQueueName(String queueName) {
        this.queueName = queueName;
        return this;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Publisher setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getModifiedAt() {
        return modifiedAt;
    }

    public Publisher setModifiedAt(Long modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Publisher setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
