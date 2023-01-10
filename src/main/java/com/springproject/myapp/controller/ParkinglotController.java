package com.springproject.myapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springproject.myapp.entity.ParkingSpace;
import com.springproject.myapp.service.IParkingSpaceService;

@RestController
public class ParkinglotController {

    @Autowired
    private IParkingSpaceService parkingSpaceService;


    @GetMapping("/parkinglot")
    public ResponseEntity<List<ParkingSpace>> getAllParkingSpaces(){
		return ResponseEntity.ok().body(parkingSpaceService.getAllParkingSpaces());
	}

    @GetMapping("/parkinglot/{id}")
    public ResponseEntity<ParkingSpace> getParkingSpaceById(@PathVariable long id){
        return ResponseEntity.ok().body(parkingSpaceService.getParkingSpaceById(id));
    }

    @GetMapping("/parkinglot/occupied")
    public ResponseEntity<List<ParkingSpace>> getAllOccupied(){
        return ResponseEntity.ok().body(parkingSpaceService.getAllOccupied());
    }

    @GetMapping("/parkinglot/free")
    public ResponseEntity<List<ParkingSpace>> getAllFree(){
        return ResponseEntity.ok().body(parkingSpaceService.getAllFree());
    }

    @GetMapping("/parkinglot/partly-occupied")
    public ResponseEntity<List<ParkingSpace>> getAllPartlyOccupied(){
        return ResponseEntity.ok().body(parkingSpaceService.getAllPartlyOccupied());
    }

    // @PostMapping("/parkinglot")
	// public ResponseEntity<ParkingSpace> addParkingSpace(@RequestBody ParkingSpace parkingSpace){
	// 	return ResponseEntity.ok().body(this.parkingSpaceService.addParkingSpace(parkingSpace));
	// }
    
    // @PutMapping("/parkinglot/{id}")   
    // public ResponseEntity<ParkingSpace> updateParkingSpace(@PathVariable long id, @RequestBody ParkingSpace parkingSpace){
    //     parkingSpace.setId(id);
    //     return ResponseEntity.ok().body(this.parkingSpaceService.updateParkingSpace(parkingSpace));
    // }
}
