package com.springproject.myapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name="Parkinglot")
public class ParkingSpace {

    public enum Status {
        Occupied,
        Free,
        PartlyOccupied
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "status")
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "parkingSpaces")
    @JsonIgnore
    private List<Vehicle> vehicles = new ArrayList<>();

    public ParkingSpace(){

    }

    public ParkingSpace(int parkId, Status status){
        super();
        this.status = status;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    public Status getStatus() {
        return status;
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return "ParkingSpace [id=" + id + ", status=" + status + ", vehicles=" + vehicles + "]";
    }

}
