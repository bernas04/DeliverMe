package com.deliverMe.tqs.services;

import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.repository.RiderRepository;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class RiderServiceTest {
    
    @InjectMocks
    private RiderService service;

    @Mock 
    private RiderRepository repository;

    @Test
    public void whenThereIsNoRiders(){
        List<Rider> emptyList = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(emptyList);
        List<Rider> returned = service.getRiders();
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
        assertThat(returned).isNotNull().isEmpty();
    }

    @Test
    public void whenSaveRiderReturIt(){
        Rider s = new Rider("João",  "29-04-2001");
        Mockito.when(repository.save(s)).thenReturn(s);
        Rider fromDB = service.saveRider(s);

        Mockito.verify(repository, VerificationModeFactory.times(1)).save(s);

        assertThat(s).isEqualTo(fromDB);
    }

    @Test
    public void whenThereIsVariousRiders(){
        Rider s1 = new Rider("João","18-06-1999");
        Rider s2 = new Rider("Maria","18-09-2000");
        Rider s3 = new Rider("Zé", "28-11-1998");

        List<Rider> allRiders = new ArrayList<>();
        allRiders.add(s1);
        allRiders.add(s2);
        allRiders.add(s3);

        Mockito.when(repository.findAll()).thenReturn(allRiders);

        List<Rider> fromDB = service.getRiders();

        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();

        assertThat(fromDB).hasSize(allRiders.size())
                          .extracting(Rider::getName)
                          .containsExactly(s1.getName(), s2.getName(), s3.getName());
    }

    @Test
    public void findByEmail(){
        Rider r = new Rider("Maria", "29-04-2001");
        r.setUsername("xpectate");
        lenient().when(repository.findByUsername("xpectate")).thenThrow(NoSuchElementException.class);
    }
}
