package com.vivek.cab.booking.database;

import com.vivek.cab.booking.exceptions.CabAlreadyExistsException;
import com.vivek.cab.booking.exceptions.CabNotFoundException;
import com.vivek.cab.booking.model.Cab;
import com.vivek.cab.booking.model.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabsManager {

  Map<String, Cab> cabs = new HashMap<>();

  public void createCab(final Cab newCab) {
    if (cabs.containsKey(newCab.getId())) {
      throw new CabAlreadyExistsException();
    }

    cabs.put(newCab.getId(), newCab);
  }

  public Cab getCab(final String cabId) {
    if (!cabs.containsKey(cabId)) {
      throw new CabNotFoundException();
    }
    return cabs.get(cabId);
  }

  public void updateCabLocation(final String cabId, final Location newLocation) {
    if (!cabs.containsKey(cabId)) {
      throw new CabNotFoundException();
    }
    cabs.get(cabId).setCurrentLocation(newLocation);
  }

  public void updateCabAvailability(final String cabId, final Boolean newAvailability) {
    if (!cabs.containsKey(cabId)) {
      throw new CabNotFoundException();
    }
    cabs.get(cabId).setIsAvailable(newAvailability);
  }

  public List<Cab> getCabs(final Location fromPoint, final Double distance) {
    List<Cab> result = new ArrayList<>();
    for (Cab cab : cabs.values()) {
      // TODO: Use epsilon comparison because of double
      if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
        result.add(cab);
      }
    }
    return result;
  }
}
