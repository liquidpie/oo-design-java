package com.vivek.map.navigator;

import com.vivek.map.navigator.behaviour.BusTB;
import com.vivek.map.navigator.behaviour.CarTB;
import com.vivek.map.navigator.behaviour.TransportBehaviour;
import com.vivek.map.navigator.behaviour.WalkTB;
import com.vivek.map.navigator.model.Location;
import com.vivek.map.navigator.model.PathNode;
import com.vivek.map.navigator.model.TransportMode;

public class Navigator {

    private static final TransportMode DEFAULT_MODE = TransportMode.CAR;
    private TransportBehaviour transportBehaviour;

    public Navigator() {
        this.transportBehaviour = getTransportBehaviour(DEFAULT_MODE);
    }

    public Navigator(TransportBehaviour transportBehaviour) {
        if (transportBehaviour == null)
            throw new IllegalArgumentException("Transport Behaviour can't be null");
        this.transportBehaviour = transportBehaviour;
    }

    public void setTransportBehaviour(TransportMode transportMode) {
        if (transportMode == null)
            throw new IllegalArgumentException("Transport Mode can't be null");
        this.transportBehaviour = getTransportBehaviour(transportMode);
    }

    public PathNode buildRoute(Location start, Location end) {
        return transportBehaviour.buildPath(start, end);
    }

    private TransportBehaviour getTransportBehaviour(TransportMode transportMode) {
        if (transportMode == TransportMode.CAR)
            return new CarTB();
        else if (transportMode == TransportMode.BUS)
            return new BusTB();
        else if (transportMode == TransportMode.WALK)
            return new WalkTB();
        return null;
    }

}
