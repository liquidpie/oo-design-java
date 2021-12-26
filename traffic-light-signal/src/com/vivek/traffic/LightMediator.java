package com.vivek.traffic;

import java.util.HashSet;
import java.util.Set;

public class LightMediator {

    Set<Light> trafficSignal = new HashSet<>();

    public void registerLight(Light light) {
        trafficSignal.add(light);
    }

    public void deRegisterLight(Light light) {
        trafficSignal.remove(light);
    }

    /**
     * When any light turns ON, it calls this method to notify mediator. Light mediator will turn OFF
     * all other light by calling turnOffAllOtherLights(light)
     * method
     */
    public void notifyMediator(Light light) {
        turnOfAllOtherLights(light);
    }

    /**
     * Turns off all the lights other than passed light Object
     */
    private void turnOfAllOtherLights(Light light) {
        for (Light l : trafficSignal) {
            if (!l.equals(light)) {
                l.turnOFF();
            }
        }
        System.out.println("--------------------");
    }

}
