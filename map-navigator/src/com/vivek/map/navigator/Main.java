package com.vivek.map.navigator;

import com.vivek.map.navigator.model.Location;
import com.vivek.map.navigator.model.TransportMode;

public class Main {

    public static void main(String[] args) {
        Navigator navigator = new Navigator();
        Location a = new Location(1.0, 1.0);
        Location b = new Location(2.0, 2.0);

        // Test Case: Default mode
        navigator.buildRoute(a, b);

        // Test Case: Walk mode
        navigator.setTransportBehaviour(TransportMode.WALK);
        navigator.buildRoute(a, b);

        // Test Case: Bus mode
        navigator.setTransportBehaviour(TransportMode.BUS);
        navigator.buildRoute(a, b);
    }

}
