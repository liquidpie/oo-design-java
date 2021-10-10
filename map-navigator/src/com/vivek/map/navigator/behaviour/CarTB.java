package com.vivek.map.navigator.behaviour;

import com.vivek.map.navigator.model.Location;
import com.vivek.map.navigator.model.PathNode;

public class CarTB implements TransportBehaviour {

    private final String type = "Car ride";

    @Override
    public PathNode buildPath(Location start, Location end) {
        PathNode startPathNode = new PathNode(start);

        System.out.println("Building " + type + " path from " + start + " to " + end);

        return startPathNode;
    }
}
