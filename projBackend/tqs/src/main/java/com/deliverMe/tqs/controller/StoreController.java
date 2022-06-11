package com.deliverMe.tqs.controller;

import java.util.List;

import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.services.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stores/")
public class StoreController {
    @Autowired
    StoreService service;

    @PostMapping("/addStore")
    public Store addStore(@RequestBody Store s){
        return service.saveStore(s);
    }

    @GetMapping("/stores")
    public List<Store> getAllStores(){
        return service.getStores();
    }

    @GetMapping("/store")
    public Store getStoreId(@RequestParam(value = "id") long id){
        return service.getStoreId(id);
    }

    @DeleteMapping("/deleteStore")
    public String deleteStore(@RequestParam(value = "id") long id){
        return service.deleteById(id);
    }
    
}
