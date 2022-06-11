package com.deliverMe.tqs.services;

import java.util.List;

import com.deliverMe.tqs.model.Client;
import com.deliverMe.tqs.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    public ClientRepository repository;

    public Client saveClient(Client s){
        return repository.save(s);
    }

    public List<Client> getClient(){
        return repository.findAll();
    }

    public Client getClientId(Long id){
        return repository.findById(id).get();
    }

    public String deleteById(Long id){
        repository.deleteById(id);
        return "Client removed!";
    }
}
