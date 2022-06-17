package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    private Manager m = new Manager("Zé", "2001-12-29", "ze@admin.pt");

    @Test
    @DisplayName("Test format")
    public void testFormatManager(){
        assertEquals(this.m.getBirthDate(), "2001-12-29");
        assertEquals(this.m.getEmail(), "ze@admin.pt");
        assertEquals(this.m.getName(), "Zé");
    }
}
