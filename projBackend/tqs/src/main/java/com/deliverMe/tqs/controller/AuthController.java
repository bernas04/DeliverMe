package com.deliverMe.tqs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.services.RiderService;

public class AuthController {
    
    @Autowired
    private RiderService riderService;

    @PostMapping("/register")
    public ResponseEntity<Rider> registRider(@RequestBody Map<String, String> riderInfo) throws Error{
        String name = riderInfo.get("name");
        String password = riderInfo.get("password");
        String email = riderInfo.get("email");

        System.out.println("--> aqui");
        if (email == null || name == null) 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        riderService.saveRider(new Rider(name, password, email));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
