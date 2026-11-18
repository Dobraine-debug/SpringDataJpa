package se.yrgo.SpringDataJpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.SpringDataJpa.data.CustomerRepository;
import se.yrgo.SpringDataJpa.data.VehicleRepository;
import se.yrgo.SpringDataJpa.data.dto.CustomerDto;
import se.yrgo.SpringDataJpa.data.dto.VehicleBrandProjectionDto;
import se.yrgo.SpringDataJpa.data.dto.VehicleDto;
import se.yrgo.SpringDataJpa.domain.Customer;
import se.yrgo.SpringDataJpa.domain.Vehicle;
import se.yrgo.SpringDataJpa.domain.VehicleBrandProjection;

import java.util.*;

@RestController
public class VehicleRestController {
    private CustomerRepository customerData;
    private VehicleRepository vehicleData;

    public VehicleRestController(CustomerRepository customerData, VehicleRepository vehicleData){
        this.customerData = customerData;
        this.vehicleData = vehicleData;
    }

    @RequestMapping("/vehicles")
    public List<Vehicle> allVehicles(){
        return vehicleData.findAll();
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestParam String name, @RequestParam String phone){
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setPhone(phone);
        customerData.save(newCustomer);
        return ResponseEntity.ok("Customer created");
    }

    @PostMapping("/vehicles")
    public ResponseEntity createVehicle(@RequestParam String registrationNumber,
                                        @RequestParam String brand, @RequestParam String model, @RequestParam int productionYear){
        Vehicle newVehicle = new Vehicle();
        newVehicle.setRegistrationNumber(registrationNumber);
        newVehicle.setBrand(brand);
        newVehicle.setProductionYear(productionYear);
        newVehicle.setModel(model);
        vehicleData.save(newVehicle);
        return new ResponseEntity<Vehicle>(newVehicle, HttpStatus.CREATED);
    }

    @PostMapping("/addvehicletocustomer")
    public ResponseEntity<String> registerVehicleToCustomer(
            @RequestParam Long customerId,
            @RequestParam Long vehicleId){
        Optional<Customer> optCustomer = customerData.findById(customerId);
        Optional<Vehicle> optVehicle = vehicleData.findById(vehicleId);
        if(optCustomer.isEmpty() || optVehicle.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid customer or vehicle id");
        }
        else{
            Customer customer = optCustomer.get();
            Vehicle vehicle = optVehicle.get();
            vehicle.setCustomer(customer);
            customer.getVehicles().add(vehicle);
            vehicleData.save(vehicle);
            return ResponseEntity.ok("Vehicle id " + vehicleId +
                    " successfully registered to customer id " + customerId);
        }
    }

    @GetMapping("/customers")
    public List<CustomerDto> allCustomersAndVehicles(){
        List<Object[]> results = customerData.findCustomersWithVehicles();
        Map<Long, CustomerDto> customerMap = new HashMap<>();
        for(Object[] result : results){
            Customer customer = (Customer) result[0];
            Vehicle vehicle = (Vehicle) result[1];

            CustomerDto customerDto = customerMap.getOrDefault(customer.getId(), new CustomerDto());
            customerDto.setCustomerId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setPhone(customer.getPhone());

            if(vehicle != null){
                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setVehicleId(vehicle.getId());
                vehicleDto.setBrand(vehicle.getBrand());
                vehicleDto.setModel(vehicle.getModel());
                vehicleDto.setProductionYear(vehicle.getProductionYear());
                vehicleDto.setRegistrationNumber(vehicle.getRegistrationNumber());

                customerDto.getVehicles().add(vehicleDto);
            }
            customerMap.put(customer.getId(), customerDto);
        }
        return new ArrayList<>(customerMap.values());
    }

    @GetMapping("/customer-id")
    public ResponseEntity<Long> getCustomerId(@RequestParam String name){
        return customerData.findByName(name)
                .map(Customer::getId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/vehicles/{id}/brand")
    public ResponseEntity<String> updateBrand(@PathVariable Long id, @RequestParam String brand ){
        vehicleData.updateBrand(brand, id);
        return ResponseEntity.ok("Successfully changed brand to " + brand + " on vehicle with id " +id);
    }

    @GetMapping("/vehicles-by-brand")
    public List<Vehicle> getVehiclesByBrand(@RequestParam String brand){
        return vehicleData.findAllByBrand(brand);
    }

    @GetMapping("/vehicle-brand-info")
    public List<VehicleBrandProjectionDto> getVehiclesBrand(){
        List<VehicleBrandProjectionDto> vehicles = new ArrayList<>();
        ArrayList<VehicleBrandProjection> projections = vehicleData.brands();
        for(VehicleBrandProjection projection : projections){
            VehicleBrandProjectionDto vehicle = new VehicleBrandProjectionDto();
            vehicle.setRegistrationNumber(projection.getRegistrationNumber());
            vehicle.setBrand(projection.getBrand());
            vehicles.add(vehicle);
        }
        return vehicles;

    }
}
