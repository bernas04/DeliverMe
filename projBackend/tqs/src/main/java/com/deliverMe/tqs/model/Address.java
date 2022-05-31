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



    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoad() {
        return this.road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public String toString() {
        return "{" +
            "Road='" + getRoad() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", zipcode='" + getZipcode() + "'" +
            "}";
    }


}
