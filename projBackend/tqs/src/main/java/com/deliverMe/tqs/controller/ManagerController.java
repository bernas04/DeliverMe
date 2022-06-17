package com.deliverMe.tqs.controller;

import java.util.HashMap;
import java.util.List;

import com.deliverMe.tqs.model.Manager;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    
    @Autowired
    ManagerService service;

    @GetMapping("/ridersInfo")
    public HashMap<Rider, List<Double>> getRidersInfo(){
        return service.getRidersInfo();
    }

    @PostMapping("/addManager")
    public Manager addManager(@RequestBody Manager m){
        
        return service.saveManager(m);
    }
}
