package com.deliverMe.tqs.services;

import java.util.List;

import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository repository;

    public Purchase addPurchase(Purchase p){
        return repository.save(p);
    }

    public List<Purchase> getAllPurchases(){
        return repository.findAll();
    }

    public Purchase getPurchaseId(Long id){
        return repository.getReferenceById(id);
    }

    public Purchase updatePurchaseCanceled(Long id){
        Purchase p = repository.getReferenceById(id);
        p.setStatusCanceled();
        return p;
    }

    public Purchase updatePurchaseInProgress(Long id){
        Purchase p = repository.getReferenceById(id);
        p.setStatusInProgress();
        return p;
    }

    public Purchase updatePurchaseDelivered(Long id){
        Purchase p = repository.getReferenceById(id);
        p.setStatusDelivered();
        return p;
    }
}
