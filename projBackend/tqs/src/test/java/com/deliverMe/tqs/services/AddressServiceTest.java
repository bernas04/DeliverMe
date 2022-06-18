package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.repository.AddressRepository;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService service;

    @Mock
    private AddressRepository repository;

    @Test
    public void whenThereIsNoAddresses(){
        List<Address> emptyList = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(emptyList);
        List<Address> allAddressInfo = service.getAddresses();
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();

        assertThat(allAddressInfo).isEmpty();
        assertThat(allAddressInfo.size()).isZero();
    }

    @Test
    public void whenAddressReturnIt(){
        Address s = new Address("Rua Central", "Aveiro", "PT","3800");
        Mockito.when(repository.save(s)).thenReturn(s);
        Address fromDB = service.saveAddress(s);

        Mockito.verify(repository, VerificationModeFactory.times(1)).save(s);

        assertThat(s).isEqualTo(fromDB);
    }

    @Test
    public void whenThereIsVariousRiders(){
        Address s1 = new Address("Rua Central", "Aveiro", "PT","3800");
        Address s2 = new Address("Rua da UA", "Aveiro", "PT","3800");
        Address s3 = new Address("Rua Mário Sacramento", "Aveiro", "PT","3800");

        List<Address> allRiders = new ArrayList<>();
        allRiders.add(s1);
        allRiders.add(s2);
        allRiders.add(s3);

        Mockito.when(repository.findAll()).thenReturn(allRiders);

        List<Address> fromDB = service.getAddresses();

        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();

        assertThat(fromDB).hasSize(allRiders.size())
                          .extracting(Address::getRoad)
                          .containsExactly(s1.getRoad(), s2.getRoad(), s3.getRoad());
    }
}
