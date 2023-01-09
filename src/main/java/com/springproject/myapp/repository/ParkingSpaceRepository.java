package com.springproject.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.myapp.entity.ParkingSpace;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

}
