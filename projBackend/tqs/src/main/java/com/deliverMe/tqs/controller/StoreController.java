package com.deliverMe.tqs.controller;

import java.util.List;

import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.StoreRepository;
import com.deliverMe.tqs.services.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/")
public class StoreController {
    @Autowired
    StoreService service;
    
    @Autowired
    StoreRepository repository;

    
}
