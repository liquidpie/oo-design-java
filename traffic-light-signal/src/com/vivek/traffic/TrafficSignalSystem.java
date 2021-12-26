package com.vivek.traffic;

public class TrafficSignalSystem {

    public static void main(String[] args) {
        LightMediator lightMediator = new LightMediator();
        Light red = new Light(Color.RED, lightMediator);
        Light green = new Light(Color.GREEN, lightMediator);
        Light yellow = new Light(Color.YELLOW, lightMediator);

        red.turnON();
        green.turnON();
        yellow.turnON();
    }

}
