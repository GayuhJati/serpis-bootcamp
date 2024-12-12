package com.techno.bootcamp.productservice.repository;

import com.techno.bootcamp.productservice.entity.ProdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdRepository extends JpaRepository<ProdEntity, Long> {
    Optional<ProdEntity> findByName(String name);
}
