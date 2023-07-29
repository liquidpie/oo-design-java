package com.vivek.observer;

public class App {

    public static void main(String[] args) {
        DimmerSwitch dimmerSwitch = new DimmerSwitch();
        dimmerSwitch.addObserver(new LightBulb(5));
        dimmerSwitch.changeBrightness(10);
    }

}
