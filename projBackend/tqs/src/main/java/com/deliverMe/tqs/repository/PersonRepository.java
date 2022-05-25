package com.deliverMe.tqs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.deliverMe.tqs.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findByEmail(String email);
}
