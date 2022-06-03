package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientTest {
    
    private static Address a = new Address("Rua da Pega", "Aveiro", "Portugal", "3870-067");
    private static Client c = new Client();

    @BeforeEach
    public void setUp(){
        c.setAddress(a);
        c.setEmail("joao@ua.pt");
        c.setName("João");
    }

    @Test
    @DisplayName("Test client")
    public void testClient(){
        assertEquals(c.getAddress(), a);
        assertEquals(c.getEmail(), "joao@ua.pt");
        assertEquals(c.getName(), "João");

        // Change parameters
        c.setEmail("antonio@ua.pt");
        assertNotEquals(c.getEmail(), "joao@ua.pt");
    }
}
