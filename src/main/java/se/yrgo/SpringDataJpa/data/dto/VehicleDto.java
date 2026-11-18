package se.yrgo.SpringDataJpa.data.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import se.yrgo.SpringDataJpa.domain.Customer;

public class VehicleDto {
    private Long vehicleId;
    private String registrationNumber;
    private String brand;
    private String model;
    private int productionYear;

    public VehicleDto(){}
    public VehicleDto(Long vehicleId, String registrationNumber, String brand, String model,
                      int productionYear, CustomerDto customer){
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    public void setVehicleId(Long vehicleId) {this.vehicleId = vehicleId;}
    public Long getId() {return vehicleId;}

    public String getRegistrationNumber() {return registrationNumber;}
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;}

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    public int getProductionYear() {return productionYear;}
    public void setProductionYear(int productionYear) {this.productionYear = productionYear;}
}
