package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
    List<Rooms> findByProduct_PidEquals(Integer pid);


}