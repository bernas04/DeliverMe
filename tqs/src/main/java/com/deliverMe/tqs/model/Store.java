package com.deliverMe.tqs.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany
    private List<Purchase> purchases;

    private Address address;

    private Double latitude, longitude;


    public Store(String name, Address address, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }


    public Store() {}

    


}
