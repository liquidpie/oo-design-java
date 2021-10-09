package com.vivek.rental.car.service;

import com.vivek.rental.car.TestData;
import com.vivek.rental.car.exception.InvalidVehicleIdException;
import com.vivek.rental.car.exception.VehicleBookedException;
import com.vivek.rental.car.model.account.User;
import com.vivek.rental.car.model.reservation.Invoice;
import com.vivek.rental.car.model.reservation.VehicleInventory;
import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.model.vehicle.HireableVehicle;
import com.vivek.rental.car.repository.UserRepository;
import com.vivek.rental.car.repository.VehicleInventoryRepository;
import com.vivek.rental.car.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceBuilderUtilTest {
    @Test
    public void shouldBuildCancelledInvoice()
            throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }

        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        vehicleReservation = userService.cancel(vehicleReservation.getReservationId());

        Invoice invoice = InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);

        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 50);
        assertEquals(invoice.getTaxes(), 9);
        assertEquals(invoice.getTotal(), 59);
    }
}
