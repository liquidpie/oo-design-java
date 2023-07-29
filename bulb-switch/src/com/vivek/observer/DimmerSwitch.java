package com.vivek.observer;

import java.util.ArrayList;
import java.util.List;

public class DimmerSwitch {

    private static final int DEFAULT_MIN_LEVEL = 5;
    private static final int DEFAULT_MAX_LEVEL = 15;

    private final int minLevel;
    private final int maxLevel;
    private final List<BrightnessObserver> observers;

    DimmerSwitch() {
        this(DEFAULT_MIN_LEVEL, DEFAULT_MAX_LEVEL);
    }

    DimmerSwitch(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.observers = new ArrayList<>();
    }

    public void addObserver(BrightnessObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BrightnessObserver observer) {
        observers.remove(observer);
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void changeBrightness(int currentLevel) {
        if (currentLevel < minLevel || currentLevel > maxLevel) {
            System.out.println("Invalid brightness level");
            return;
        }
        double brightness = (double) (currentLevel - minLevel) / (maxLevel - minLevel);

        notifyObservers(brightness);
    }

    private void notifyObservers(double brightness) {
        for (BrightnessObserver observer : observers) {
            observer.updateBrightness(brightness);
        }
    }

}
