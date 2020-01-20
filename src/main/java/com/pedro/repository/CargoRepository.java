package com.pedro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
