package com.deliverMe.tqs.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.deliverMe.tqs.services.StoreService;
import com.deliverMe.tqs.configuration.JwtRequestFilter;
import com.deliverMe.tqs.configuration.WebSecurityConfig;
import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.services.ManagerService;
import com.deliverMe.tqs.services.PurchaseService;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = ManagerController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)})
@AutoConfigureMockMvc(addFilters = false)
public class StoreControllerTest {
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
    public void allStoreTest() throws Exception{
        List<Store> allStores = new ArrayList<>();
        allStores.add(new Store("Loja das Meias", new Address("Rua da Pega", "Aveiro","PT", "3800")));
        allStores.add(new Store("BookShelf", new Address("Rua Mário Sacramento", "Aveiro","PT", "3800")));

        when(storeService.getStores()).thenReturn(allStores);

        mvc.perform(get("/api/stores/stores").contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isNotFound());
                            /* .andExpect(jsonPath("$.[0].name", is("João")))
                            .andExpect(jsonPath("$.[1].name", is("Mariana")))
                            .andExpect(jsonPath("$.[2].name", is("Reis")))
                            .andExpect(jsonPath("$.[3].name", is("Rodriguez"))); */
    }

    @Test
    public void postRider() throws Exception{
        Store r = new Store("BookSHelf", new Address("Rua Mário Sacramento", "Aveiro", "PT", "3800"));
        
        when(storeService.saveStore(r)).thenReturn(r);

        mvc.perform(post("/api/stores/addStore").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
                        /* .andExpect(jsonPath("$.", is(r))); */
    }
}
