package com.deliverMe.tqs.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.deliverMe.tqs.configuration.JwtRequestFilter;
import com.deliverMe.tqs.configuration.WebSecurityConfig;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.ManagerService;
import com.deliverMe.tqs.services.PurchaseService;
import com.deliverMe.tqs.services.StoreService;


@WebMvcTest(value = ManagerController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)})
@AutoConfigureMockMvc(addFilters = false)
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
        riderInfo.put(new Rider("Joaquina", "20-08-2001"), tmp);
        riderInfo.put(new Rider("Mário", "20-08-2000"), tmp);
        riderInfo.put(new Rider("Filipe", "15-07-1998"), tmp);


        when(managerService.getRidersInfo()).thenReturn(riderInfo);

        mvc.perform(get("/api/manager/ridersInfo")
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                                                    .andExpect(jsonPath("$.*", hasSize(4)));
                                                    // Each Map<Rider, List<Double>> coresponds to 1

    }
}
