package com.vivek.mqs.db.schema;

public class Queue {

    private String name;
    private Integer maxSize;
    private Long createdAt;
    private Long modifiedAt;
    private boolean isActive;

    public Queue(String name, Integer maxSize) {
        this.name = name;
        this.maxSize = maxSize;
        this.isActive = true;
    }

    public String getName() {
        return name;
    }

    public Queue setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public Queue setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Queue setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getModifiedAt() {
        return modifiedAt;
    }

    public Queue setModifiedAt(Long modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public Queue setActive(boolean active) {
        isActive = active;
        return this;
    }
}
