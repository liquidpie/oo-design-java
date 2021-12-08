package com.vivek.cab.booking.model;

enum TripStatus {
  IN_PROGRESS,
  FINISHED
}

public class Trip {
  private Rider rider;
  private Cab cab;
  private TripStatus status;
  private Double price;
  private Location fromPoint;
  private Location toPoint;

  public Trip(
      final Rider rider,
      final Cab cab,
      final Double price,
      final Location fromPoint,
      final Location toPoint) {
    this.rider = rider;
    this.cab = cab;
    this.price = price;
    this.fromPoint = fromPoint;
    this.toPoint = toPoint;
    this.status = TripStatus.IN_PROGRESS;
  }

  public void endTrip() {
    this.status = TripStatus.FINISHED;
  }

  @Override
  public String toString() {
    return "Trip{" +
            "rider=" + rider +
            ", cab=" + cab +
            ", status=" + status +
            ", price=" + price +
            ", fromPoint=" + fromPoint +
            ", toPoint=" + toPoint +
            '}';
  }
}
