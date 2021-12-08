package com.vivek.cab.booking.strategies;

import com.vivek.cab.booking.model.Location;

public interface PricingStrategy {
  Double findPrice(Location fromPoint, Location toPoint);
}
