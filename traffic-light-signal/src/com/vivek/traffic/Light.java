package com.vivek.traffic;

public class Light {

    enum State { ON, OFF }

    private Color color;
    private State currentState;
    private LightMediator lightMediator;

    public Light(Color color, LightMediator lightMediator) {
        this.color = color;
        this.lightMediator = lightMediator;
        this.lightMediator.registerLight(this);
    }

    void turnON() {
        currentState = State.ON;
        System.out.println(color.name() + " is turned ON");
        lightMediator.notifyMediator(this);
    }

    void turnOFF() {
        currentState = State.OFF;
        System.out.println(color.name() + " is turned OFF");
    }

}
