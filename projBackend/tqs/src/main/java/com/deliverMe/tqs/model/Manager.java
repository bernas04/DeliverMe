package com.deliverMe.tqs.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Manager extends Person{
    public Manager(String name, String birthDate, String email){
        super(name, birthDate, email);
    }

    public Manager(){}
}
