package com.vivek.fitness.slot.booking.service;

import com.vivek.fitness.slot.booking.model.WaitlistRequest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class WaitlistService {

    private static final Map<String, Queue<WaitlistRequest>> WAITLIST_REQUESTS = new HashMap<>();

    public void addToWaitlist(WaitlistRequest waitlistRequest) {
        Queue<WaitlistRequest> slotWaitlistQueue = WAITLIST_REQUESTS.get(waitlistRequest.getSlotId());
        if (slotWaitlistQueue == null) {
            slotWaitlistQueue = new LinkedList<>();
        }
        slotWaitlistQueue.add(waitlistRequest);
        WAITLIST_REQUESTS.put(waitlistRequest.getSlotId(), slotWaitlistQueue);
    }

    public WaitlistRequest getWaitlistRequest(String slotId) {
        Queue<WaitlistRequest> slotWaitlistQueue = WAITLIST_REQUESTS.get(slotId);
        if (slotWaitlistQueue == null) {
            return null;
        }

        return slotWaitlistQueue.poll();
    }
}
