package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    private Manager m = new Manager("Zé", "admin" ,"2001-12-29", "ze");

    @Test
    @DisplayName("Test format")
    public void testFormatManager(){
        assertEquals(this.m.getBirthDate(), "2001-12-29");
        assertEquals(this.m.getUsername(), "ze");
        assertEquals(this.m.getName(), "Zé");
    }
}
