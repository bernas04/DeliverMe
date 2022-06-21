package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.List;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.OrderStatus;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.PurchaseRepository;
import com.deliverMe.tqs.repository.RiderRepository;
import com.deliverMe.tqs.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private RiderRepository riderRepository;

    public Purchase addPurchase(Purchase p){
        if (p.getStore()==null){
            List<Store> allStores = storeRepository.findAll();
            if (allStores.size()>0){
                Store toAdd = allStores.get(0);
                p.setStore(toAdd);
                p.setStatus(OrderStatus.REQUESTED);
            }
            else{
                Store tmp =storeRepository.save(new Store("BookShelf", new Address("Rua Lourenço Peixinho", "Aveiro", "Portugal", "3800")));
                p.setStore(tmp);
                p.setStatus(OrderStatus.REQUESTED);
            }
        }
        System.out.println("--> " +p);
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

    public Purchase updatePurchaseDelivered(Long id){
        Purchase p = repository.getReferenceById(id);
        p.setOrderCompleted();
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

    public List<Purchase> findAllByRider(Long id){
        Rider r = riderRepository.findById(id).get();
        List<Purchase> allPurchases= repository.findAllByRider(r);
        List<Purchase> onGoingDeliveries = new ArrayList<>();

        for (Purchase p : allPurchases){
            if (p.getStatus().equals(OrderStatus.IN_PROGRESS)){
                onGoingDeliveries.add(p);
            }
        }
        return onGoingDeliveries;
    }

    public List<Purchase> findByRider(Long id){
        Rider r = riderRepository.findById(id).get();
        return repository.findAllByRider(r);
    }
}
