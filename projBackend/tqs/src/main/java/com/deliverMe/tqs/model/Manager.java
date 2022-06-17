package com.deliverMe.tqs.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Manager extends Person{
    public Manager(String name, String password ,String birthDate, String username){
        super(name, password ,birthDate, username);
    }

    public Manager(){}
}
