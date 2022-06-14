package com.deliverMe.tqs.services;

import java.util.List;

import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.repository.RiderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RiderService {
    
    @Autowired
    private RiderRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    public Rider saveRider(Rider r) throws Error{
        if (repository.findByEmail(r.getEmail()).isEmpty()){
            r.setPassword(encoder.encode(r.getPassword()));
        }
        return repository.save(r);

    }

    public List<Rider> getRiders(){
        return repository.findAll();
    }

    public Rider getRiderId(Long id){
        return repository.findById(id).get();
    }

    public String deleteById(Long id){
        repository.deleteById(id);
        return "Rider deleted!";
    }
}
