package com.example.rentify.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String place;
    private String area;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private String nearbyHospitals;
    private String nearbyColleges;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}
	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}
	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}
	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}
	public String getNearbyHospitals() {
		return nearbyHospitals;
	}
	public void setNearbyHospitals(String nearbyHospitals) {
		this.nearbyHospitals = nearbyHospitals;
	}
	public String getNearbyColleges() {
		return nearbyColleges;
	}
	public void setNearbyColleges(String nearbyColleges) {
		this.nearbyColleges = nearbyColleges;
	}
    
}
