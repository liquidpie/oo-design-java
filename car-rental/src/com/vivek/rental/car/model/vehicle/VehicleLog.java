package com.vivek.rental.car.model.vehicle;

import java.time.LocalDateTime;

public class VehicleLog {
    private String vehicleLogId;
    private String vehicleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String description;
    private VehicleLogType vehicleLogType;

    public String getVehicleLogId() {
        return vehicleLogId;
    }

    public void setVehicleLogId(String vehicleLogId) {
        this.vehicleLogId = vehicleLogId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleLogType getVehicleLogType() {
        return vehicleLogType;
    }

    public void setVehicleLogType(VehicleLogType vehicleLogType) {
        this.vehicleLogType = vehicleLogType;
    }
}
