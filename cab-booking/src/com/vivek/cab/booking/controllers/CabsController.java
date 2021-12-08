package com.vivek.cab.booking.controllers;

import com.vivek.cab.booking.database.CabsManager;
import com.vivek.cab.booking.database.TripsManager;
import com.vivek.cab.booking.model.Cab;
import com.vivek.cab.booking.model.Location;

public class CabsController {
  private CabsManager cabsManager;
  private TripsManager tripsManager;

  public CabsController(CabsManager cabsManager, TripsManager tripsManager) {
    this.cabsManager = cabsManager;
    this.tripsManager = tripsManager;
  }

  public void regiserCab(final String cabId, final String driverName) {
    cabsManager.createCab(new Cab(cabId, driverName));
  }

  public void updateCabLocation(final String cabId, final Double newX, final Double newY) {
    cabsManager.updateCabLocation(cabId, new Location(newX, newY));
  }

  public void updateCabAvailability(final String cabId, final Boolean newAvailability) {
    cabsManager.updateCabAvailability(cabId, newAvailability);
  }

  public void endTrip(final String cabId) {
    tripsManager.endTrip(cabsManager.getCab(cabId));
  }
}
