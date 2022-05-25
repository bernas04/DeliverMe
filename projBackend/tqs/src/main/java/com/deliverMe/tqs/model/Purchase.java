package com.deliverMe.tqs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="purchase")
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

    private Client client;

    @OneToOne
    private Address address;
    
    private Long deliverTs;

    private int riderReview;


    public Purchase(Address address, Store store, Client client, Long deliverTs, int riderReview) {
        this.date = new Date();
        this.store = store;
        this.client = client;
        this.deliverTs = deliverTs;
        this.riderReview = riderReview;
        this.address = address;
    }


    public Purchase() {}


}
