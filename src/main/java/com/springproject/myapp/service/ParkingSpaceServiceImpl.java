package com.springproject.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.springproject.myapp.entity.ParkingSpace;
import com.springproject.myapp.exception.ResourceNotFoundException;
import com.springproject.myapp.repository.ParkingSpaceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParkingSpaceServiceImpl implements IParkingSpaceService {

    ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    public void setParkingSpaceRepository(ParkingSpaceRepository parkingSpaceRepository){
        this.parkingSpaceRepository = parkingSpaceRepository;
    }
    
    @Override
    public ParkingSpace addParkingSpace(ParkingSpace parkingSpace) {
        return parkingSpaceRepository.save(parkingSpace);
    }

    @Override
    public List<ParkingSpace> getAllParkingSpaces() {
        return this.parkingSpaceRepository.findAll();
    }

    @Override
    public ParkingSpace getParkingSpaceById(long id) {
        Optional<ParkingSpace> parkingSpaceDb = this.parkingSpaceRepository.findById(id);

        if(parkingSpaceDb.isPresent()) {
            return parkingSpaceDb.get();
        }
        else{
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }

    @Override
    public ParkingSpace updateParkingSpace(ParkingSpace parkingSpace) {
        Optional<ParkingSpace> parkingSpaceDb = this.parkingSpaceRepository.findById(parkingSpace.getId());
		
		if(parkingSpaceDb.isPresent()) {
			ParkingSpace parkingSpaceUpdate = parkingSpaceDb.get();
			parkingSpaceUpdate.setId(parkingSpace.getId());
            parkingSpaceUpdate.setParkId(parkingSpace.getParkId());
            parkingSpaceUpdate.setStatus(parkingSpace.getStatus());
            parkingSpaceUpdate.setVehicles(parkingSpace.getVehicles());
			parkingSpaceRepository.save(parkingSpaceUpdate);
			return parkingSpaceUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + parkingSpace.getId());
		}
    }
    
}
