package com.springproject.myapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.myapp.entity.ParkingSpace;
import com.springproject.myapp.entity.Vehicle;
import com.springproject.myapp.entity.ParkingSpace.Status;
import com.springproject.myapp.entity.Vehicle.Type;
import com.springproject.myapp.exception.ResourceNotFoundException;
import com.springproject.myapp.repository.VehicleRepository;


@Service
@Transactional
public class VehicleServiceImpl implements IVehicleService {

	VehicleRepository vehicleRepository;
    
    @Autowired
    private IParkingSpaceService parkingSpaceService;

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        Optional<Vehicle> vehicleDb = this.vehicleRepository.findById(vehicle.getId());
		
		if(vehicleDb.isPresent()) {
			Vehicle vehicleUpdate = vehicleDb.get();
			vehicleUpdate.setId(vehicle.getId());
            vehicleUpdate.setType(vehicle.getType());
            vehicleUpdate.setParkingSpaces(vehicle.getParkingSpaces());
            vehicleUpdate.setEntryTime(vehicle.getEntryTime());
            vehicleUpdate.setExitTime(vehicle.getExitTime());
            vehicleUpdate.setParkingSpaces(vehicle.getParkingSpaces());
			vehicleRepository.save(vehicleUpdate);
			return vehicleUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + vehicle.getId());
		}

	}

    @Override
    public Vehicle addVehicleWithSpace(Vehicle vehicle, long[] parkingId) {
        ParkingSpace parkingSpace;
        List<ParkingSpace> p1;
        ParkingSpace p;
        List<Vehicle> v1 = Arrays.asList(vehicle);
        switch (vehicle.getType().name()){

            case "Motorbike":
            parkingSpace = this.parkingSpaceService.getParkingSpaceById(parkingId[0]);
            p1 = Arrays.asList(parkingSpace);
            if (parkingSpace.getStatus().name() == "Free"){
                vehicle.setParkingSpaces(p1);
                parkingSpace.setStatus(Status.PartlyOccupied);
                parkingSpace.setVehicles(v1);
                return vehicleRepository.save(vehicle);
            }
            if (parkingSpace.getStatus().name() == "PartlyOccupied"){
                vehicle.setParkingSpaces(p1);
                parkingSpace.setStatus(Status.Occupied);
                List<Vehicle> tmpLst = parkingSpace.getVehicles();
                tmpLst.add(vehicle);
                parkingSpace.setVehicles(tmpLst);
                return vehicleRepository.save(vehicle);
            }
            else {
                throw new ResourceNotFoundException("parking space " + parkingId[0] + " not free");
            }
            
            case "Car":
            parkingSpace = this.parkingSpaceService.getParkingSpaceById(parkingId[0]);
            p1 = Arrays.asList(parkingSpace);
            if (parkingSpace.getStatus().name() == "Free"){
                vehicle.setParkingSpaces(p1);
                parkingSpace.setVehicles(v1);
                parkingSpace.setStatus(Status.Occupied);
                return vehicleRepository.save(vehicle);
            }
            else {
                throw new ResourceNotFoundException("parking space " + parkingId[0] + " not free");
            }

            case "Bus":
            if (parkingId.length == 4){
                p1 = new ArrayList<ParkingSpace>();
                for (int i=0; i<4; i++){ 
                    p = this.parkingSpaceService.getParkingSpaceById(parkingId[i]);
                    if (p.getStatus() == Status.Free){
                        p1.add(p);
                    }
                }
                if ((p1.size() == 4) && busCanPark(parkingId)){
                    vehicle.setParkingSpaces(p1);
                    for (int i=0; i<4; i++){
                        this.parkingSpaceService.getParkingSpaceById(parkingId[i]).setStatus(Status.Occupied);
                        this.parkingSpaceService.getParkingSpaceById(parkingId[i]).setVehicles(v1);
                    }
                    return vehicleRepository.save(vehicle);
                }
                else {
                    throw new ResourceNotFoundException("parking spaces " + parkingId.toString() + " not free");
                }
            }
            throw new ResourceNotFoundException("needs 4 parking spaces ");

        }
        throw new ResourceNotFoundException("something is wrong with the request");
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
    }

    private boolean busCanPark(long[] parkId){
        Arrays.sort(parkId);
        if ((parkId[3] < 101) && (parkId[0] == parkId[1] -1) && (parkId[1] == parkId[2] -1) && (parkId[2] == parkId[3] -1)){
            return true;
        }
        return false;
    }

    @Override
    public Vehicle getVehicleById(long vehicleId) {
        Optional<Vehicle> vehicleDb = this.vehicleRepository.findById(vehicleId);
        if(vehicleDb.isPresent()) {
            return vehicleDb.get();
        }
        else{
            throw new ResourceNotFoundException("Record not found with id : " + vehicleId);
        }
    }

    @Override
    public Vehicle exitVehicle(long vehicleId) {
        Optional<Vehicle> vehicleDb = this.vehicleRepository.findById(vehicleId);
		
		if(vehicleDb.isPresent()) {
			Vehicle vehicleUpdate = vehicleDb.get();
            vehicleUpdate.setExitTime(LocalDateTime.now());
            List<ParkingSpace> parkingSpaces = vehicleUpdate.getParkingSpaces();
            for (ParkingSpace parkingSpace : parkingSpaces) {
                if (vehicleUpdate.getType() == Type.Motorbike && parkingSpace.getStatus() == Status.Occupied){
                    parkingSpace.setStatus(Status.PartlyOccupied);
                    List<Vehicle> tmpLst = parkingSpace.getVehicles();
                    tmpLst.remove(vehicleUpdate);
                    parkingSpace.setVehicles(tmpLst);
                }
                else {
                    parkingSpace.setStatus(Status.Free);
                    parkingSpace.setVehicles(null);
                }
            }
            vehicleUpdate.setParkingSpaces(null);
			vehicleRepository.save(vehicleUpdate);
			return vehicleUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + vehicleId);
		}
        
    }
    
}
