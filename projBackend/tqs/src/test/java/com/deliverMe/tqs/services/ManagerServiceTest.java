package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.ManagerRepository;
import com.deliverMe.tqs.repository.PurchaseRepository;
import com.deliverMe.tqs.repository.RiderRepository;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private RiderRepository riderRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private ManagerService managerService;

    @InjectMocks
    private PurchaseService purchaseService;


    @Test
    public void getRidersInfoWhenNoRiders(){
        List<Rider> emptyList = new ArrayList<>();
        Mockito.when(riderRepository.findAll()).thenReturn(emptyList);
        Map<Rider, List<Double>> allRidersInfo = managerService.getRidersInfo();
        Mockito.verify(riderRepository, VerificationModeFactory.times(1)).findAll();

        assertThat(allRidersInfo.keySet()).isEmpty();
        assertThat(allRidersInfo.size()).isZero();
    }

    @Test
    public void whenAddRidersHaveInfo(){
        Rider r = new Rider("Bernas", "29-04-2001");
        Address a = new Address("Rua da UA", "Aveiro", "Portugal", "3800");
        Address storeAddress = new Address("Rua da Estia", "Campia", "Portugal", "3670");
        Store s = new Store("Loja das meias", storeAddress);
        Purchase p = new Purchase(s, "Mariana", a);

        r.setPurchases(Arrays.asList(p));
        r.setAverageReview(4.5);
        r.setTotalReviews(2);

        Mockito.when(riderRepository.findAll()).thenReturn(Arrays.asList(r));

        Map<Rider, List<Double>> riderInfo = managerService.getRidersInfo();

        Mockito.verify(riderRepository, VerificationModeFactory.times(1)).findAll();

        assertThat(riderInfo.keySet()).hasSize(1).extracting("name").contains(r.getName());
        assertThat(riderInfo.keySet()).hasSize(1).extracting(Rider::getPurchases).containsExactly(Arrays.asList(p));

        assertThat(riderInfo.keySet()).hasSize(1).extracting(Rider::getBirthDate).isInstanceOf(ArrayList.class);
    }

}
