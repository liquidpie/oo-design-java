package com.vivek.cab.booking.controllers;

import com.vivek.cab.booking.database.RidersManager;
import com.vivek.cab.booking.database.TripsManager;
import com.vivek.cab.booking.model.Location;
import com.vivek.cab.booking.model.Rider;
import com.vivek.cab.booking.model.Trip;

import java.util.List;

public class RidersController {
  private RidersManager ridersManager;
  private TripsManager tripsManager;

  public RidersController(RidersManager ridersManager, TripsManager tripsManager) {
    this.ridersManager = ridersManager;
    this.tripsManager = tripsManager;
  }

  public void registerRider(final String riderId, final String riderName) {
    ridersManager.createRider(new Rider(riderId, riderName));
  }

  public void book(
      final String riderId,
      final Double sourceX,
      final Double sourceY,
      final Double destX,
      final Double destY) {

    tripsManager.createTrip(
        ridersManager.getRider(riderId),
        new Location(sourceX, sourceY),
        new Location(destX, destY));
  }

  public List<Trip> fetchHistory(final String riderId) {
    List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
    return trips;
  }
}
