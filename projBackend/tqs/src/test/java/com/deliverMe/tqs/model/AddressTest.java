package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddressTest {
    
    private static List<Address> addressList= new ArrayList<>();
    
    static Address a1 = new Address("Rua da Pega", "Aveiro", "Portugal", "3870-067");
    static Address a2 = new Address("Rua da Universidade", "Aveiro", "Portugal", "3870-090");
    static Address a3 = new Address("Rua do Fórum", "Aveiro", "Portugal", "3870-091");


    @BeforeAll
    public static void setUp(){
        addressList.add(a1);
        addressList.add(a2);
        addressList.add(a3);
    }

    @Test
    @DisplayName("Test data and formats")
    public void testDataAndFormats(){
        assertEquals(addressList.get(1).getCity(), a2.getCity());
        assertEquals(addressList.get(2).getCity(), a3.getCity());
        
        assertEquals(addressList.get(1).getZipcode(), a2.getZipcode());
        assertEquals(addressList.get(2).getZipcode(), a3.getZipcode());

        assertEquals(addressList.get(1).getCountry(), a2.getCountry());
        assertEquals(addressList.get(2).getCountry(), a3.getCountry());

        assertEquals(addressList.get(1).getRoad(), a2.getRoad());
        assertEquals(addressList.get(2).getRoad(), a3.getRoad());
    }

    @Test
    @DisplayName("Test list manipulation")
    public void testListManipulation(){
        assertEquals(addressList.size(), 3);
        addressList.remove(0);
        assertEquals(addressList.size(), 2);
        addressList.contains(a2);
        addressList.clear();
        assertEquals(addressList.size(), 0);
    }

}
