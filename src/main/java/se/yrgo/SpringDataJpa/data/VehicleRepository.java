package se.yrgo.SpringDataJpa.data;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.yrgo.SpringDataJpa.domain.Vehicle;
import se.yrgo.SpringDataJpa.domain.VehicleBrandProjection;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByBrand(String brand);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Vehicle AS v
        SET v.brand = :brand
        WHERE v.vehicleId = :vehicleId
    """)
    int updateBrand(String brand, Long vehicleId);

    @Query("SELECT v.registrationNumber AS registrationNumber, v.brand AS brand FROM Vehicle AS v")
    ArrayList<VehicleBrandProjection> brands();

}
