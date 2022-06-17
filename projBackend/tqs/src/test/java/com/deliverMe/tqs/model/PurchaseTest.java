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
        Rider r = new Rider("José", "2000-04-21", "jose@ua.pt");
        Store s = new Store();

        s.setName("Loja das meias");

        p.setDate(new Date(2022, 07,03,15,21));
        p.setRider(r);
        p.setStore(s);

        this.listPurchases.add(new Purchase(s, "Maria", new Address("Rua da Pega", "Aveiro", "Portugal", "3800")));
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

}
