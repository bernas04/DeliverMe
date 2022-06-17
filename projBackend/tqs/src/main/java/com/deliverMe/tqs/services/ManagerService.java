package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.deliverMe.tqs.model.Manager;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.repository.ManagerRepository;
import com.deliverMe.tqs.repository.RiderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    
    @Autowired
    public RiderRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public ManagerRepository managerRepository;

    public HashMap<Rider, List<Double>> getRidersInfo(){
        List<Rider> listRiders = repository.findAll();
        HashMap<Rider, List<Double>> riderInfo = new HashMap<>();
        for (Rider r: listRiders){
            r.getTotalReviews();
            List<Double> tmp = new ArrayList<>();
            tmp.add(r.getAverageReview());
            tmp.add((double) r.getTotalReviews());
            riderInfo.put(r, tmp);
        }
        return riderInfo;
    }

    public Manager saveManager(Manager m){
        m.setPassword(bcryptEncoder.encode(m.getPassword()));
        return managerRepository.save(m);
    }

    public List<Manager> getManagers(){
        return managerRepository.findAll();
    }
}
