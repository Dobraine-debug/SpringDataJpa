package se.yrgo.SpringDataJpa.data.dto;

import se.yrgo.SpringDataJpa.domain.VehicleBrandProjection;

public class VehicleBrandProjectionDto {
    private String registrationNumber;
    private String brand;

    public VehicleBrandProjectionDto(){}
    public VehicleBrandProjectionDto(String registrationNumber, String brand){
        this.registrationNumber = registrationNumber;
        this.brand = brand;
    }

    public String getRegistrationNumber() {return registrationNumber;}
    public void setRegistrationNumber(String registrationNumber) {this.registrationNumber = registrationNumber;}

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}
}
