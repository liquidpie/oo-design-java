package com.vivek.fantasy.cricket.constraints;

public enum TeamCombination {
    
    COMBINATION1(3, 3, 1);

    private final int batsmanCount;
    private final int bowlerCount;
    private final int keeperCount;

    TeamCombination(int batsmanCount, int bowlerCount, int keeperCount) {
        this.batsmanCount = batsmanCount;
        this.bowlerCount = bowlerCount;
        this.keeperCount = keeperCount;
    }

    public int getBatsmanCount() {
        return batsmanCount;
    }

    public int getBowlerCount() {
        return bowlerCount;
    }

    public int getKeeperCount() {
        return keeperCount;
    }
}
