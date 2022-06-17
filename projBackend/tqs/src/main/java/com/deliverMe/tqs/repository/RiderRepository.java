package com.deliverMe.tqs.repository;

import java.util.Optional;

import com.deliverMe.tqs.model.Rider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUsername(String username);
}
