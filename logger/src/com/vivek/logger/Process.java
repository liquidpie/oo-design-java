package com.vivek.logger;

public class Process {

    private final String id;
    private final long startTime;
    private long endTime;

    public Process(final String id, final long startTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = -1;
    }

    public String getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
