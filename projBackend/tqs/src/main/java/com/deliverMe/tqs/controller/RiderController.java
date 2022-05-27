package com.deliverMe.tqs.controller;

import java.util.List;

import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.RiderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/riders")
public class RiderController {
    
    @Autowired
    RiderService service;

    @PostMapping("/addRider")
    public Rider addRidder(@RequestBody Rider r){
        return service.saveRider(r);
    }

    @GetMapping("/Riders")
    public List<Rider> getAllRiders(){
        return service.getRiders();
    }
    
    @GetMapping("/Rider")
    public Rider getRiderId(@RequestParam (value = "id") Long id){
        return service.getRiderId(id);
    }

    @DeleteMapping("deleteRider")
    public String deleteRider(@RequestParam (value = "id") Long id){
        return service.deleteById(id);
    }

}
