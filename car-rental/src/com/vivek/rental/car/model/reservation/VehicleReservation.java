package com.vivek.rental.car.model.reservation;

import com.vivek.rental.car.model.common.Address;
import com.vivek.rental.car.model.vehicle.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleReservation {
    private String reservationId;
    private String usrId;
    private LocalDateTime reservationDate;
    private ReservationStatus status;
    private LocalDateTime fromDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private Address pickupLocation;
    private Address dropLocation;
    private double startMileage;
    private double endMileage;
    private String accocatedVehicleId;
    private VehicleType vehicleType;
    private String invoiceId;
    private List<VehicleAddon> vehicleAddons;
    private List<AddonService> addonServices;
    private VehicleReservationType vehicleReservationType;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
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

    public Address getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Address pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Address getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(Address dropLocation) {
        this.dropLocation = dropLocation;
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

    public String getAccocatedVehicleId() {
        return accocatedVehicleId;
    }

    public void setAccocatedVehicleId(String accocatedVehicleId) {
        this.accocatedVehicleId = accocatedVehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<VehicleAddon> getVehicleAddons() {
        return vehicleAddons;
    }

    public void setVehicleAddons(List<VehicleAddon> vehicleAddons) {
        this.vehicleAddons = vehicleAddons;
    }

    public List<AddonService> getAddonServices() {
        return addonServices;
    }

    public void setAddonServices(List<AddonService> addonServices) {
        this.addonServices = addonServices;
    }

    public VehicleReservationType getVehicleReservationType() {
        return vehicleReservationType;
    }

    public void setVehicleReservationType(VehicleReservationType vehicleReservationType) {
        this.vehicleReservationType = vehicleReservationType;
    }
}
