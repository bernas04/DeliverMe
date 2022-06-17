package com.deliverMe.tqs.repository;

import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByRider(Rider r);
}
