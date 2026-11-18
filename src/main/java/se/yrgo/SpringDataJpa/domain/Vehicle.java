package se.yrgo.SpringDataJpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String registrationNumber;
    private String brand;
    private String model;
    private int productionYear;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vehicles_customers",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Customer customer;

    public Vehicle(){}

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

    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, registrationNumber, brand, model, productionYear, customer);
    }
}
