package com.vivek.observer;

public class LightBulb implements BrightnessObserver {

    private final int watt;
    private double currentBrightness;

    public LightBulb(int watt) {
        this.watt = watt;
    }

    @Override
    public void updateBrightness(double level) {
        this.currentBrightness = watt * level;
        System.out.printf("The %s watt bulb is %s bright%n", watt, currentBrightness);
    }

    public int getWatt() {
        return watt;
    }

    public double getCurrentBrightness() {
        return currentBrightness;
    }
}
