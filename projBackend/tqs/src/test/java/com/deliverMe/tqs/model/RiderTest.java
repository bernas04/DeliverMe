package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiderTest {
    
    private static Rider r = new Rider("Ze", "1967, 04, 25", "rider@rider.pt");
    private static List<Purchase> listPurchase = new ArrayList<>();
    
    @BeforeAll
    public static void setUp(){
        Purchase p = new Purchase();
        listPurchase.add(p);
        r.setPurchases(listPurchase);
    }

    @Test
    @DisplayName("Test Rider")
    public void testRider(){
        r.setAverageReview(5);
        r.setTotalReviews(1);

        assertEquals(r.getAverageReview(), 5);
        assertEquals(r.getTotalReviews(), 1);

        assertEquals(listPurchase.size(), 1);
        listPurchase.clear();
        assertEquals(listPurchase.size(), 0);
    }

}
