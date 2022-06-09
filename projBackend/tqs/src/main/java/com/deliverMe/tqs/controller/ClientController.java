package com.deliverMe.tqs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverMe.tqs.model.Client;
import com.deliverMe.tqs.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    @Autowired
    ClientService service;

    @PostMapping("/addRider")
    public Client addRidder(@RequestBody Client r){
        return service.saveClient(r);
    }

    @GetMapping("/clients")
    public List<Client> allClients(){
        return service.getClient();
    }

    @GetMapping("/client")
    public Client getClient(@RequestParam (value = "id") Long id){
        return service.getClientId(id);
    }

    @DeleteMapping("/deleteClient")
    public String deleteClient(@RequestParam (value = "id") Long id){
        return service.deleteById(id);
    }
}
