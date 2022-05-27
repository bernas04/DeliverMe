package com.deliverMe.tqs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Client client;

    @OneToOne
    private Address address;
    

    private int riderReview;

    private int deliverTimeStamp;

    private OrderStatus status;


    public Purchase(Address address, Store store, Client client, int riderReview) {
        this.date = new Date();
        this.store = store;
        this.client = client;
        this.riderReview = riderReview;
        this.address = address;
        this.status = OrderStatus.REQUESTED;
    }


    public Purchase() {}

    public void setStatusCanceled(){
        this.status=OrderStatus.CANCELED;
    }

    public void setStatusInProgress(){
        this.status=OrderStatus.IN_PROGRESS;
    }

    public void setStatusDelivered(){
        this.status=OrderStatus.DELIVERED;
    }

}
