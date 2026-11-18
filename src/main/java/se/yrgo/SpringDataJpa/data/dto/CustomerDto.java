package se.yrgo.SpringDataJpa.data.dto;

import jakarta.persistence.OneToMany;
import se.yrgo.SpringDataJpa.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    private Long customerId;

    private String name;
    private String phone;

    private List<VehicleDto> vehicles = new ArrayList<>();


    public CustomerDto(){}
    public CustomerDto(Long customerId, String name, String phone, List<VehicleDto> vehicles){
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.vehicles = vehicles;
    }

    public Long getCustomerId() {return customerId;}
    public void setCustomerId(Long customerId) {this.customerId = customerId;}

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setPhone(String phone) {this.phone = phone;}
    public String getPhone() {return phone;}

    public List<VehicleDto> getVehicles() {return vehicles;}

    public void setVehicles(List<VehicleDto> vehicles) {this.vehicles = vehicles;}
}
