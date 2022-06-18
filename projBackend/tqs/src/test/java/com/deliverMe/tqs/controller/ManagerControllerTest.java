package com.deliverMe.tqs.controller;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deliverMe.tqs.configuration.JwtRequestFilter;
import com.deliverMe.tqs.configuration.WebSecurityConfig;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.ManagerService;
import com.deliverMe.tqs.services.PurchaseService;
import com.deliverMe.tqs.services.StoreService;

@WebMvcTest(value = ManagerController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)})
public class ManagerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StoreService storeService;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @Test
    public void ridersInfoTest() throws Exception{
        HashMap<Rider, List<Double>> riderInfo = new HashMap<>();
        List<Double> tmp = new ArrayList<>();
        tmp.add(4.0);
        tmp.add(4.0);

        riderInfo.put(new Rider("Zé", "08-09-1999"), tmp);
        when(managerService.getRidersInfo()).thenReturn(riderInfo);

        mvc.perform(get("/api/manager/ridersInfo")
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());

        verify(managerService, times(0)).getRidersInfo();
    }
}
