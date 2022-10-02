package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByCityEqualsIgnoreCaseAndDistrictEqualsIgnoreCase(String city, String district);

}