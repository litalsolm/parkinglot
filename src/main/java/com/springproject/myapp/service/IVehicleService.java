package com.springproject.myapp.service;

import com.springproject.myapp.entity.Vehicle;
import java.util.List;

public interface IVehicleService {

    Vehicle addVehicle (Vehicle vehicle);
    Vehicle updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(long vehicleId);
	Vehicle exitVehicle(long vehicleId);
    Vehicle addVehicleWithSpace(Vehicle vehicle, long[] parkingId);

}
