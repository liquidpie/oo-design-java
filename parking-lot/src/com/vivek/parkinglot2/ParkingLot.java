package com.vivek.parkinglot2;

import java.util.*;

public class ParkingLot {

    Map<SizeType, Map<EntryGate, List<ParkingSpace>>> availableParkingSpacesBySize;

    Map<Vehicle, Ticket> parkedVehicles;

    boolean isParked(Vehicle v) {
        return null != parkedVehicles.get(v);
    }

    private Ticket parkBySize(Vehicle vehicle, SizeType sizeType) {
        Ticket ticket = null;
        Map<EntryGate, List<ParkingSpace>> matchParking = availableParkingSpacesBySize.get(sizeType);
        if (!matchParking.isEmpty()) {
            ParkingSpace allotedParkingSpace = null;
            // prefer nearest entry gate
            EntryGate allotedEntryGate = vehicle.entryGate;
            List<ParkingSpace> availableParkingSpaces = matchParking.get(allotedEntryGate);

            if (!availableParkingSpaces.isEmpty()) {
                allotedParkingSpace = availableParkingSpaces.get(0);
            }
            else {
                Iterator<Map.Entry<EntryGate, List<ParkingSpace>>> itr = matchParking.entrySet().iterator();
                Map.Entry<EntryGate, List<ParkingSpace>> entry = itr.next();
                allotedEntryGate = entry.getKey();
                availableParkingSpaces = entry.getValue();
                allotedParkingSpace = availableParkingSpaces.get(0);
            }

            Long id = null; // generate some unique ticket id from DB
            ticket = new Ticket(id, new Date(), vehicle, allotedParkingSpace);

            parkedVehicles.put(vehicle, ticket);

            availableParkingSpaces.remove(0);

            if (availableParkingSpaces.isEmpty()) {
                matchParking.remove(allotedEntryGate);
            }

            // update vehicle ticket details in DB
        }

        return ticket;
    }

    synchronized Ticket park(Vehicle vehicle) {
        if (parkedVehicles.get(vehicle) != null) {
            // throw vehicle is already parked exception
        }

        Ticket ticket = null;

        if (!availableParkingSpacesBySize.isEmpty()) {
            ticket = parkBySize(vehicle, vehicle.size);
            if (null == ticket && vehicle.size != SizeType.LARGE) {
                if (vehicle.size == SizeType.MEDIUM) {
                    ticket = parkBySize(vehicle, SizeType.SMALL);
                }
                else if (vehicle.size == SizeType.SMALL) {
                    ticket = parkBySize(vehicle, SizeType.MEDIUM);
                    if (null == ticket) {
                        ticket = parkBySize(vehicle, SizeType.LARGE);
                    }
                }
            }
        }

        return ticket;
    }

    synchronized boolean unpark(Ticket ticket) {
        Vehicle vehicle = ticket.vehicle;

        Ticket t1 = parkedVehicles.get(vehicle);

        if (t1 == null) {
            // throw vehicle parking entry missing
        }

        if (!t1.equals(ticket)) {
            // throw vehicle parking ticket mismatch
        }

        ParkingSpace parkingSpaceToBeFreed = ticket.allottedParkingSpace;

        Map<EntryGate, List<ParkingSpace>> parkingSpacesBySize = availableParkingSpacesBySize.get(parkingSpaceToBeFreed.size);

        if (null == parkingSpacesBySize) {
            parkingSpacesBySize = new HashMap<EntryGate, List<ParkingSpace>>();
        }

        List<ParkingSpace> parkingSpacesByEntryGate = parkingSpacesBySize.get(parkingSpaceToBeFreed.nearByGate);

        if (null == parkingSpacesByEntryGate) {
            parkingSpacesByEntryGate = new ArrayList<ParkingSpace>();
        }

        parkingSpacesByEntryGate.add(parkingSpaceToBeFreed);

        parkedVehicles.remove(vehicle);

        return true;
    }

    enum SizeType {
        SMALL, MEDIUM, LARGE;
    }

    class EntryGate {
        String name;
    }

    class ParkingSpace {
        String id;
        SizeType size;
        EntryGate nearByGate;
    }

    class Vehicle {
        String number;
        SizeType size;
        EntryGate entryGate;
    }

    class Ticket {
        Long id;
        Date inTime;
        Vehicle vehicle;
        ParkingSpace allottedParkingSpace;

        public Ticket(Long id, Date inTime, Vehicle v, ParkingSpace p) {
            this.id = id;
            this.inTime = inTime;
            this.vehicle = v;
            this.allottedParkingSpace = p;
        }
    }
}