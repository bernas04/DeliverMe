package com.deliverMe.tqs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagerTest {
    private Manager m = new Manager("Zé", new Date(2001, 12, 29), "ze@admin.pt");

    @Test
    @DisplayName("Test format")
    public void testFormatManager(){
        assertEquals(this.m.getBirthDate(), new Date(2001, 12, 29));
        assertEquals(this.m.getEmail(), "ze@admin.pt");
        assertEquals(this.m.getName(), "Zé");
    }
}
