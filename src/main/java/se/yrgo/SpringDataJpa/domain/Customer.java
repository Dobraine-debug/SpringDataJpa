package se.yrgo.SpringDataJpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Vehicle> vehicles = new HashSet<>();


    public Customer(){}

    public Long getId() {return customerId;}

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setPhone(String phone) {this.phone = phone;}
    public String getPhone() {return phone;}

    public Set<Vehicle> getVehicles() {return vehicles;}
    public void setVehicles(Set<Vehicle> vehicles) {this.vehicles = vehicles;}
}
