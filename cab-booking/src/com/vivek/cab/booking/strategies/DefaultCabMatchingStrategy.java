package com.vivek.cab.booking.strategies;

import com.vivek.cab.booking.model.Cab;
import com.vivek.cab.booking.model.Location;
import com.vivek.cab.booking.model.Rider;

import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

  @Override
  public Cab matchCabToRider(
      final Rider rider,
      final List<Cab> candidateCabs,
      final Location fromPoint,
      final Location toPoint) {
    if (candidateCabs.isEmpty()) {
      return null;
    }
    return candidateCabs.get(0);
  }
}
