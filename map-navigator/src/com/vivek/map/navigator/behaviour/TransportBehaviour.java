package com.vivek.map.navigator.behaviour;

import com.vivek.map.navigator.model.Location;
import com.vivek.map.navigator.model.PathNode;

public interface TransportBehaviour {

    PathNode buildPath(Location start, Location end);

}
