package com.deliverMe.tqs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Client extends Person{

    @OneToOne
    private Address address;

    public Client(String name, Date birthDate, String email, Address address){
        super(name, birthDate, email);
        this.address=address;
    }
}
