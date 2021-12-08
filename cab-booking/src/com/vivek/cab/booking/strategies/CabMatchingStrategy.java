package com.vivek.cab.booking.strategies;

import com.vivek.cab.booking.model.Cab;
import com.vivek.cab.booking.model.Location;
import com.vivek.cab.booking.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {

  Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
