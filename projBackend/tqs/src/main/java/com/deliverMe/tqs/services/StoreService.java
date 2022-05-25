package com.deliverMe.tqs.services;

import java.util.List;

import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.PurchaseRepository;
import com.deliverMe.tqs.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    
    @Autowired
    private StoreRepository repository;


    public Store saveStore(Store s){
        return repository.save(s);
    }

    public List<Store> getStores(){
        return repository.findAll();
    }

    public Store getStoreId(Long id){
        return repository.getReferenceById(id);
    }

    public String deleteById(Long id){
        repository.deleteById(id);
        return "Store removed!!";
    }
}
