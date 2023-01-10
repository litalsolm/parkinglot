package com.springproject.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.myapp.entity.Vehicle;
import com.springproject.myapp.service.IVehicleService;


@RestController
public class VehicleController {

    @Autowired
    private IVehicleService vehicleService;


    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
		return ResponseEntity.ok().body(vehicleService.getAllVehicles());
	}

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable long id){
        return ResponseEntity.ok().body(vehicleService.getVehicleById(id));
    }

    @GetMapping("/vehicles/cost/{id}")
    public double getTotalCost(@PathVariable long id){
        return vehicleService.getTotalCost(id);
    }

    // @PostMapping("/vehicles")
	// public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
	// 	return ResponseEntity.ok().body(this.vehicleService.addVehicle(vehicle));
	// }

    @PostMapping("/vehicles/parking/{id_list}")
	public ResponseEntity<Vehicle> addVehicleWithSpace(@RequestBody Vehicle vehicle, @PathVariable long[] id_list){
		return ResponseEntity.ok().body(this.vehicleService.addVehicleWithSpace(vehicle, id_list));

	}
    
    @PutMapping("/vehicles/{id}")   
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable long id, @RequestBody Vehicle vehicle){
        vehicle.setId(id);
        return ResponseEntity.ok().body(this.vehicleService.updateVehicle(vehicle));
    }

    @PutMapping("/vehicles/exit/{id}")
    public ResponseEntity<Vehicle> exitVehicle(@PathVariable long id){
        return ResponseEntity.ok().body(this.vehicleService.exitVehicle(id));
    }
}
