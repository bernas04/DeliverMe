package com.deliverMe.tqs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Rider extends Person{

    private double averageReview;

    private int totalReviews;

    @OneToMany
    @JsonIgnore
    private List<Purchase> purchases;


    public Rider(String name, String password ,String birthDate, String username) {
        super(name, password ,birthDate, username);
        this.averageReview = 0.0;
        this.totalReviews = 0;
        purchases=new ArrayList<>();
    }

    public Rider(String name, String birthDate) {
        super(name, birthDate);
        this.averageReview = 0.0;
        this.totalReviews = 0;
        purchases=new ArrayList<>();
    }


    public Rider(){}


    public void updateAverageAndTotalOrders(int rate){
        this.averageReview = (this.averageReview*(double) this.totalReviews + rate)/++this.totalReviews;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public double getAverageReview() {
        return this.averageReview;
    }

    public void setAverageReview(double averageReview) {
        this.averageReview = averageReview;
    }

    public int getTotalReviews() {
        return this.totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public List<Purchase> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addOnePurchase(Purchase p){
        purchases.add(p);
    }


}
