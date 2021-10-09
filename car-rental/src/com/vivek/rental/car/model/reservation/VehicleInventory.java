package com.vivek.rental.car.model.reservation;

import com.vivek.rental.car.model.vehicle.HireableVehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class VehicleInventory {
    private String id;
    private String reservationId;
    private LocalDateTime reservationDate;
    private ReservationStatus status;
    private LocalDateTime fromDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private double startMileage;
    private double endMileage;
    private HireableVehicle vehicle;
    private VehicleReservationType vehicleReservationType;

    public VehicleInventory() {
    }

    public VehicleInventory(VehicleReservation vehicleReservation,
                            HireableVehicle hireableVehicle) {
        this.id = UUID.randomUUID().toString();
        this.reservationId = vehicleReservation.getReservationId();
        this.reservationDate = vehicleReservation.getReservationDate();
        this.status = vehicleReservation.getStatus();
        this.fromDate = vehicleReservation.getFromDate();
        this.dueDate = vehicleReservation.getDueDate();
        this.returnDate = vehicleReservation.getReturnDate();
        this.startMileage = vehicleReservation.getStartMileage();
        this.endMileage = vehicleReservation.getEndMileage();
        this.vehicle = hireableVehicle;
        this.vehicleReservationType = vehicleReservation.getVehicleReservationType();
    }

    public VehicleInventory(HireableVehicle hireableVehicle) {
        this.id = UUID.randomUUID().toString();
        this.startMileage = hireableVehicle.getMileage();
        this.endMileage = hireableVehicle.getMileage();
        this.vehicle = hireableVehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public double getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(double startMileage) {
        this.startMileage = startMileage;
    }

    public double getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(double endMileage) {
        this.endMileage = endMileage;
    }

    public HireableVehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(HireableVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleReservationType getVehicleReservationType() {
        return vehicleReservationType;
    }

    public void setVehicleReservationType(VehicleReservationType vehicleReservationType) {
        this.vehicleReservationType = vehicleReservationType;
    }
}
