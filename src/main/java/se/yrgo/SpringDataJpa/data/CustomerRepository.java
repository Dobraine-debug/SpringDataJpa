package se.yrgo.SpringDataJpa.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yrgo.SpringDataJpa.domain.Customer;
import se.yrgo.SpringDataJpa.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    @Query("SELECT c.vehicles FROM Customer AS c WHERE c.customerId = :customerId")
    List<Vehicle> findVehiclesByCustomer(@Param("customerId")Long customerId);

    @Query("SELECT c, v FROM Customer c LEFT JOIN c.vehicles v")
    List<Object[]> findCustomersWithVehicles();
}
