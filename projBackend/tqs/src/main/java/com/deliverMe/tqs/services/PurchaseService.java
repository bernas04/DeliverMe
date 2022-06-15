package com.deliverMe.tqs.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import com.deliverMe.tqs.model.OrderStatus;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.repository.PurchaseRepository;
import com.deliverMe.tqs.repository.RiderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private RiderRepository riderRepository;

    public Purchase addPurchase(Purchase p){
        return repository.save(p);
    }

    public List<Purchase> getAllPurchases(){
        List<Purchase> all = repository.findAll(); 
        return all;
    }

    public Purchase getPurchaseId(Long id){
        return repository.findById(id).get();
    }

    public Purchase updatePurchaseCanceled(Long id){
        Purchase p = repository.getReferenceById(id);
        p.setStatusCanceled();
        repository.save(p);
        return p;
    }

    public Purchase updatePurchaseInProgress(Long id, Long RiderId){
        Purchase p = repository.getReferenceById(id);
        Rider r = riderRepository.findById(RiderId).get();

        p.setStatusInProgress(r);

        repository.save(p);
        return p;
    }

    public Purchase updatePurchaseDelivered(Long id, int rate){
        Purchase p = repository.getReferenceById(id);
        p.setRiderReview(rate);
        repository.save(p);
        return p;
    }

    public List<Purchase> getRequestPurchase(){
        List<Purchase> requestPurchases = new ArrayList<>();
        for (Purchase p : repository.findAll()){
            if (p.getStatus().equals(OrderStatus.REQUESTED)){
                requestPurchases.add(p);
            }
        }
        return requestPurchases;
    }

    public List<Purchase> getCanceledPurchase(){
        List<Purchase> canceledPurchases = new ArrayList<>();
        for (Purchase p : repository.findAll()){
            if (p.getStatus().equals(OrderStatus.CANCELED)){
                canceledPurchases.add(p);
            }
        }
        return canceledPurchases;
    }

    public List<Purchase> getDeliveredPurchase(){
        List<Purchase> deliveredPurchases = new ArrayList<>();
        for (Purchase p : repository.findAll()){
            if (p.getStatus().equals(OrderStatus.DELIVERED)){
                deliveredPurchases.add(p);
            }
        }
        return deliveredPurchases;
    }

    public List<Purchase> getInProgressPurchase(){
        List<Purchase> inProgressPurchases = new ArrayList<>();
        for (Purchase p : repository.findAll()){
            if (p.getStatus().equals(OrderStatus.IN_PROGRESS)){
                inProgressPurchases.add(p);
            }
        }
        return inProgressPurchases;
    }
}
