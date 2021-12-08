package com.vivek.cab.booking.model;

public class Rider {
  String id;
  String name;

  public Rider(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Rider{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
