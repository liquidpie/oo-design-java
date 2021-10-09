package com.vivek.rental.car.model.vehicle;

import com.vivek.rental.car.model.common.Coordinates;

public abstract class HireableVehicle {
    private String id;
    private String licensePlateNumber;
    private String qrCode;
    private String make;
    private String model;
    private int yearOfManufacture;
    private double mileage;
    private int numberOfSeats;
    private VehicleCategory vehicleCategory;
    private VehicleStatus vehicleStatus;
    private VehicleType vehicleType;
    private VehicleLocation parkedLocation;
    private Coordinates currentLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleLocation getParkedLocation() {
        return parkedLocation;
    }

    public void setParkedLocation(VehicleLocation parkedLocation) {
        this.parkedLocation = parkedLocation;
    }

    public Coordinates getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Coordinates currentLocation) {
        this.currentLocation = currentLocation;
    }
}
