package com.vivek.cab.booking.database;


import com.vivek.cab.booking.exceptions.RiderAlreadyExistsException;
import com.vivek.cab.booking.exceptions.RiderNotFoundException;
import com.vivek.cab.booking.model.Rider;

import java.util.HashMap;
import java.util.Map;

/** In memory database for storing jobs. */
public class RidersManager {
  Map<String, Rider> riders = new HashMap<>();

  public void createRider(final Rider newRider) {
    if (riders.containsKey(newRider.getId())) {
      throw new RiderAlreadyExistsException();
    }

    riders.put(newRider.getId(), newRider);
  }

  public Rider getRider(final String riderId) {
    if (!riders.containsKey(riderId)) {
      throw new RiderNotFoundException();
    }
    return riders.get(riderId);
  }
}
