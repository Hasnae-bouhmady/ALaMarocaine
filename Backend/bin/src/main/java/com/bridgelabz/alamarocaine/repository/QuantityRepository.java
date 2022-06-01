package com.bridgelabz.alamarocaine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.entity.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long> {

}