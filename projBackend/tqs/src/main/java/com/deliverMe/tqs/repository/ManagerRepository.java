package com.deliverMe.tqs.repository;

import java.util.Optional;

import com.deliverMe.tqs.model.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUsername(String username);
}
