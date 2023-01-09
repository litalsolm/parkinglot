package com.springproject.myapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springproject.myapp.entity.Vehicle;
import com.springproject.myapp.entity.Vehicle.Type;
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


		// ParkingSpace space1 = new ParkingSpace(1, Status.Free);
		// parkingSpaceService.addParkingSpace(space1);
		// ParkingSpace space2 = new ParkingSpace(2, Status.Free);
		// parkingSpaceService.addParkingSpace(space2);
		// ParkingSpace space3 = new ParkingSpace(3, Status.Free);
		// parkingSpaceService.addParkingSpace(space3);
		// ParkingSpace space4 = new ParkingSpace(4, Status.Free);
		// parkingSpaceService.addParkingSpace(space4);
		// ParkingSpace space5 = new ParkingSpace(5, Status.Free);
		// parkingSpaceService.addParkingSpace(space5);
		// ParkingSpace space6 = new ParkingSpace(6, Status.Free);
		// parkingSpaceService.addParkingSpace(space6);

		// List<ParkingSpace> p1 = Arrays.asList(space1);
		// List<ParkingSpace> p2 = Arrays.asList(space2);
		// List<ParkingSpace> p3 = Arrays.asList(space3, space4, space5, space6);

		// Vehicle vehicle1 = new Vehicle(Type.Car, p1);
		// vehicleService.addVehicle(vehicle1);
		// Vehicle vehicle2 = new Vehicle(Type.Motorbike, p2);
		// vehicleService.addVehicle(vehicle2);
		// Vehicle vehicle3 = new Vehicle(Type.Motorbike, p2);
		// vehicleService.addVehicle(vehicle3);
		// Vehicle vehicle4 = new Vehicle(Type.Bus, p3);
		// vehicleService.addVehicle(vehicle4);


		
		
	}

}
