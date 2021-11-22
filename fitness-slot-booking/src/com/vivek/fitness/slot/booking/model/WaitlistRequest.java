package com.vivek.fitness.slot.booking.model;

import java.time.LocalDateTime;

public class WaitlistRequest {

    private final String slotId;
    private final String userId;
    private final LocalDateTime createdAt;

    public WaitlistRequest(String slotId, String userId) {
        this.slotId = slotId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public String getSlotId() {
        return slotId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
