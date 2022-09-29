package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
}