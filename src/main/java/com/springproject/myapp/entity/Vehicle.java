package com.springproject.myapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Vehicles")
public class Vehicle {

    public enum Type {
        Car,
        Bus,
        Motorbike
      }

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private long id;

      @Column(name = "type")
      private Type type;

      @Column(name = "entryTime")
      private LocalDateTime entryTime;

      @Column(name = "exitTime")
      private LocalDateTime exitTime;

      @Column(name = "cost")
      private double cost;

      @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
      @JsonIgnore
      @JoinTable(name = "vehicles_spaces",
		joinColumns = { @JoinColumn(name = "vehicle_id")},
		inverseJoinColumns = { @JoinColumn (name = "parkingSpace_id")})
      private List<ParkingSpace> parkingSpaces = new ArrayList<>();

      public Vehicle(){
        this.entryTime = LocalDateTime.now();
      }
      
      public Vehicle(Type type, List<ParkingSpace> parkingSpaces){
        super();
        this.type = type;
        this.entryTime = LocalDateTime.now();
        this.parkingSpaces = parkingSpaces;
      }

      public Vehicle(Type type){
        super();
        this.type = type;
        this.entryTime = LocalDateTime.now();
      }
      
      public long getId(){
        return id;
      }

      public Type getType(){
        return type;
      }


      public LocalDateTime getEntryTime(){
        return entryTime;
      }

      public LocalDateTime getExitTime(){
        return exitTime;
      }

      public void setId(long id){
        this.id = id;
      }

      public void setType(Type type){
        this.type = type;
      }


      public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setEntryTime(LocalDateTime entryTime){
        this.entryTime = entryTime;
      }

      public void setExitTime(LocalDateTime exitTime){
        this.exitTime = exitTime;
      }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public void setCost(double cost) {
      this.cost = cost;
    }

    public double getCost() {
      return cost;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", type=" + type + ", entryTime=" + entryTime + ", exitTime=" + exitTime
                + ", parkingSpaces=" + parkingSpaces + "]";
    }

    
}
