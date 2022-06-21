package com.deliverMe.tqs.controller;

import java.util.List;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.repository.AddressRepository;
import com.deliverMe.tqs.services.AddressService;
import com.deliverMe.tqs.services.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    
    @Autowired
    PurchaseService service;

    @Autowired
    AddressRepository addressService;

    @PostMapping("/addPurchase")
    public Purchase addPurchase(@RequestBody Purchase p){
        return service.addPurchase(p);
    }

    @GetMapping("/Purchases")
    public List<Purchase> getAllPurchases(){
        return service.getAllPurchases();
    }
    
    @GetMapping("/Purchase")
    public Purchase getAllPurchases(@RequestParam(value = "id") Long id){
        return service.getPurchaseId(id);
    }

    @PutMapping("/cancelPurchase")
    public Purchase cancelPurchase(@RequestParam(value = "id") Long id){
        return service.updatePurchaseCanceled(id);
    }

    @PutMapping("/confirmPurchase/{pid}")
    public Purchase confirmPurchase(@RequestParam(value = "riderId") Long id, @PathVariable(value = "pid") Long purchaseID){
        return service.updatePurchaseInProgress(purchaseID, id);
    }
    
    @PutMapping("/deliverPurchase")
    public Purchase deliveredPurchase(@RequestParam(value = "id") Long id){
        return service.updatePurchaseDelivered(id);
    }

    @GetMapping("/requestedPurchase")
    public List<Purchase> getRequestPurchases(){
        return service.getRequestPurchase();
    }

    @GetMapping("/canceledPurchase")
    public List<Purchase> getCanceledPurchases(){
        return service.getCanceledPurchase();
    }

    @GetMapping("/deliveredPurchase")
    public List<Purchase> getDeliveredPurchases(){
        return service.getDeliveredPurchase();
    }

    @GetMapping("/inProgressPurchase")
    public List<Purchase> getInProgressPurchases(){
        return service.getInProgressPurchase();
    }

    @GetMapping("/byRider")
    public List<Purchase> getPurchasesByRider(@RequestParam(value = "riderId") Long id){
        return service.findAllByRider(id);
    }

    @GetMapping("/allByRider")
    public List<Purchase> getAllPurchasesByRider(@RequestParam(value = "riderId") Long id){
        return service.findByRider(id);
    }
}
