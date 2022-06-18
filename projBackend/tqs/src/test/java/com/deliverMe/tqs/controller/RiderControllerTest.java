package com.deliverMe.tqs.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.Matchers.is;

import com.deliverMe.tqs.configuration.JwtRequestFilter;
import com.deliverMe.tqs.configuration.WebSecurityConfig;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.ManagerService;
import com.deliverMe.tqs.services.PurchaseService;
import com.deliverMe.tqs.services.RiderService;
import com.deliverMe.tqs.services.StoreService;

@WebMvcTest(value = RiderController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)})
@AutoConfigureMockMvc(addFilters = false)
public class RiderControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RiderService riderService;

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @Test
    public void allRidersTest() throws Exception{
        List<Rider> allRiders = new ArrayList<>();
        allRiders.add(new Rider("João", "19-04-1987"));
        allRiders.add(new Rider("Mariana", "10-06-1976"));
        allRiders.add(new Rider("Reis", "19-03-1988"));
        allRiders.add(new Rider("Rodriguez", "01-05-1956"));

        when(riderService.getRiders()).thenReturn(allRiders);

        mvc.perform(get("/api/riders/Riders").contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.[0].name", is("João")))
                            .andExpect(jsonPath("$.[1].name", is("Mariana")))
                            .andExpect(jsonPath("$.[2].name", is("Reis")))
                            .andExpect(jsonPath("$.[3].name", is("Rodriguez")));
    }

    @Test
    public void postRider() throws Exception{
        Rider r = new Rider("Bernas", "29-04-2001");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String exempleBody = "{\"name\":\"Bernas\", \"birthDate\":\"29-04-2001\"}";
        
        when(riderService.saveRider(r)).thenReturn(r);

        mvc.perform(post("/api/riders/addRider")
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers)
                        .content(exempleBody)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


}
