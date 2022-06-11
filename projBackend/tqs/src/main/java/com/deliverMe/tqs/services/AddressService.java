package com.deliverMe.tqs.services;

import java.util.List;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    
    @Autowired
    public AddressRepository repository;

    public Address saveAddress(Address s){
        return repository.save(s);
    }

    public List<Address> getAddresses(){
        return repository.findAll();
    }

    public Address getAddressId(Long id){
        return repository.findById(id).get();
    }

    public String deleteById(Long id){
        repository.deleteById(id);
        return "Address removed!";
    }
}
