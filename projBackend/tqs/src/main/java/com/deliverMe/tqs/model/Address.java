package com.deliverMe.tqs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String road, city, country, zipcode;

    @OneToOne
    private Store store;

    @OneToOne
    private Client client;

    public Address(String road, String city, String country, String zipcode) {
        this.road = road;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public Address(){}

}
