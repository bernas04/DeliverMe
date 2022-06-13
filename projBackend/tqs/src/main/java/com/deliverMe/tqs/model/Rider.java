package com.deliverMe.tqs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Rider extends Person{

    private double averageReview;

    private int totalReviews;

    @OneToMany
    private List<Purchase> purchases;


    public Rider(String name, String birthDate, String email) {
        super(name, birthDate, email);
        this.averageReview = 0.0;
        this.totalReviews = 0;
    }

    public Rider(){}

    /* public void completeOrder(int rate){
        Double allRatesSum = (double)this.totalReviews*this.averageReview;
        allRatesSum+=rate;
        this.totalReviews++;
        this.averageReview=allRatesSum/this.totalReviews;
    } */


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

}
