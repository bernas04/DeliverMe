package com.deliverMe.tqs.repository;

import com.deliverMe.tqs.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
