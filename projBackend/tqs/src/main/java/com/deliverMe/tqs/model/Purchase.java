package com.deliverMe.tqs.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Client client;

    
    private int riderReview;

    private Timestamp deliverTimeStamp;

    private OrderStatus status;


    public Purchase(Store store, Client client, int riderReview) {
        this.date = new Date();
        this.store = store;
        this.client = client;
        this.riderReview = riderReview;
        this.status = OrderStatus.REQUESTED;
    }


    public Purchase() {}

    public void setStatusCanceled(){
        this.status=OrderStatus.CANCELED;
    }

    public void setStatusInProgress(){
        this.status=OrderStatus.IN_PROGRESS;
    }

    public void setStatusDelivered(int rate){
        this.status=OrderStatus.DELIVERED;
        this.deliverTimeStamp = new Timestamp(System.currentTimeMillis());

        if (rate <=5 && rate>=0){
            
            this.riderReview = rate;
        }
    }


    public long getId() {
        return this.id;
    }


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Rider getRider() {
        return this.rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
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

    public int getRiderReview() {
        return this.riderReview;
    }

    public void setRiderReview(int riderReview) {
        this.riderReview = riderReview;
    }

    public Timestamp getDeliverTimeStamp() {
        return this.deliverTimeStamp;
    }

    public void setDeliverTimeStamp(Timestamp deliverTimeStamp) {
        this.deliverTimeStamp = deliverTimeStamp;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
