package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseTest {
    private List<Purchase> listPurchases = new ArrayList<>();
    
    @BeforeEach
    public void setUp(){
        Purchase p = new Purchase();
        Client c = new Client();
        Rider r = new Rider("José", new Date(2000,04,21), "jose@ua.pt");
        Store s = new Store();

        r.setAverageReview(5.0);
        r.setTotalReviews(1);

        c.setName("Joaquina");
        s.setName("Loja das meias");

        p.setClient(c);
        p.setDate(new Date(2022, 07,03,15,21));
        p.setRider(r);
        p.setStore(s);

        this.listPurchases.add(p);
    }

    @Test
    @DisplayName("Test cancel an order")
    public void testCancelOrder(){
        this.listPurchases.get(0).setStatusCanceled();
        assertEquals(this.listPurchases.get(0).getStatus(), OrderStatus.CANCELED);
    }

    @Test
    @DisplayName("Test in progress Orders")
    public void testInProgressOrders(){
        this.listPurchases.get(0).setStatusInProgress();
        assertEquals(this.listPurchases.get(0).getStatus(), OrderStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("Test delivered order")
    public void testCompleteOrder(){
        assertEquals(this.listPurchases.get(0).getStore().getName(), "Loja das meias");
        assertEquals(this.listPurchases.get(0).getRider().getName(), "José");
        assertEquals(this.listPurchases.get(0).getRider().getAverageReview(), 5.0);

        this.listPurchases.get(0).setStatusDelivered(4);

        assertEquals(this.listPurchases.get(0).getRider().getAverageReview(), 4.5);
        assertEquals(this.listPurchases.get(0).getRider().getTotalReviews(), 2);
    }

    @Test
    @DisplayName("Test wrong deliver order")
    public void testWrongCompleteOrder(){
        assertEquals(this.listPurchases.get(0).getRider().getAverageReview(), 5.0);
        this.listPurchases.get(0).setStatusDelivered(10);
        assertEquals(this.listPurchases.get(0).getRider().getAverageReview(), 5.0);
        assertEquals(this.listPurchases.get(0).getRider().getTotalReviews(), 1);
    }
}
