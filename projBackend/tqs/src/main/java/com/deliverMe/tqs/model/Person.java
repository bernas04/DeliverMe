package com.deliverMe.tqs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    
    private String password;


    private String birthDate;

    private String token;

    @Column(unique = true)
    private String username;


    public Person(String name, String password ,String birthDate, String username) {
        this.name = name;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
    }

    public Person(){}


    public Long getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    public String getToken(){return this.token;}

    public void setName(String name) {
        this.name = name;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return this.password;
    }


    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", password='" + getPassword() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", email='" + getUsername() + "'" +
            "}";
    }
    


}
