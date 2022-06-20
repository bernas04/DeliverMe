package com.deliverMe.tqs.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @OneToMany
    private List<Purchase> purchases;

    @OneToOne
    private Address address;

    private Double latitude, longitude;


    public Store(String name, Address address, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        if (latitude>=-90 && latitude<=90)
            this.latitude = latitude;
        if (longitude>=-180 && longitude<=180)
            this.longitude = longitude;
    }

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }


    public Store() {}

    
    // Getters and Setters


    public long getId() {
        return this.id;
    }

    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Purchase> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        if (latitude>=-90 && latitude<=90)
            this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        if (longitude>=-180 && longitude<=180)
            this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", purchases='" + getPurchases() + "'" +
            ", address='" + getAddress() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }



}
