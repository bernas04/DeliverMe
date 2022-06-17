package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StoreTest {
    
    private Address a = new Address("Rua da Pega", "Aveiro", "Portugal", "3800");
    private Store s = new Store("Loja das Meias", a, 60.6, -50.0);
    
    @Test
    @DisplayName("Test set wrong latitude and longitude")
    public void testWrongCoordinates(){
        assertEquals(this.s.getLatitude(), 60.6);
        assertEquals(this.s.getLongitude(), -50.0);

        this.s.setLatitude(120.0);
        this.s.setLongitude(190.0);

        assertEquals(this.s.getLatitude(), 60.6);
        assertEquals(this.s.getLongitude(), -50.0);
    }

    @Test
    @DisplayName("Test data format")
    public void testDataFormat(){
        assertEquals(this.s.getAddress(), this.a);
        assertEquals(this.s.getName(), "Loja das Meias");
    }
}
