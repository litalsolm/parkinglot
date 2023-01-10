package com.springproject.myapp.service;

import com.springproject.myapp.entity.ParkingSpace;
import java.util.List;


public interface IParkingSpaceService {

    ParkingSpace addParkingSpace(ParkingSpace parkingSpace);
    ParkingSpace updateParkingSpace(ParkingSpace parkingSpace);
    List<ParkingSpace> getAllParkingSpaces();
    List<ParkingSpace> getAllOccupied();
    List<ParkingSpace> getAllFree();
    List<ParkingSpace> getAllPartlyOccupied();
    ParkingSpace getParkingSpaceById(long id);

}
