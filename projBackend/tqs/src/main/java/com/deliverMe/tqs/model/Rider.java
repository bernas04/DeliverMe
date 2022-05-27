package com.deliverMe.tqs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Rider extends Person{

    private double averageReview;

    private int totalReviews;

    @OneToMany(mappedBy = "rider")
    private List<Purchase> purchases;


    public Rider(String name, Date birthDate, String email) {
        super(name, birthDate, email);
        this.averageReview = 0.0;
        this.totalReviews = 0;
    }

    public Rider(){}

}
