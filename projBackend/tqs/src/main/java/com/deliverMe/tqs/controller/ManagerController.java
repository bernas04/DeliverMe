package com.deliverMe.tqs.controller;

import java.util.HashMap;
import java.util.List;

import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    
    @Autowired
    ManagerService service;

    @GetMapping("/ridersInfo")
    public HashMap<Rider, List<Double>> getRidersInfo(){
        return service.getRidersInfo();
    }
}
