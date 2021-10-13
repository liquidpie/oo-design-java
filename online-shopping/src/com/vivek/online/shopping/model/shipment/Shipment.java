package com.vivek.online.shopping.model.shipment;

import java.time.LocalDate;
import java.util.List;

public class Shipment {

    private String shipmentNumber;
    private LocalDate shipmentDate;
    private LocalDate estimatedArrival;
    private String shipmentMethod;
    private List<ShipmentLog> shipmentLogs;

    void addShipmentLog(ShipmentLog shipmentLog) { }

}
