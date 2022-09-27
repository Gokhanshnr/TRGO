package com.gokhan.trgo.repositories;

import com.gokhan.trgo.entities.Taxonomy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxonomyRepository extends JpaRepository<Taxonomy, Integer> {
    Optional<Taxonomy> findByNameEqualsIgnoreCase(String name);

}