package com.springproject.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springproject.myapp.entity.ParkingSpace;
import com.springproject.myapp.entity.ParkingSpace.Status;
import com.springproject.myapp.service.IParkingSpaceService;
import com.springproject.myapp.service.IVehicleService;


@SpringBootApplication
public class MyAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}

	@Autowired
	IVehicleService vehicleService;

	@Autowired
	IParkingSpaceService parkingSpaceService;

	@Override
	public void run(String... args) throws Exception {

		for (int i=1; i<301; i++){
			ParkingSpace space1 = new ParkingSpace(i, Status.Free);
			parkingSpaceService.addParkingSpace(space1);
		}		
		
	}

}
