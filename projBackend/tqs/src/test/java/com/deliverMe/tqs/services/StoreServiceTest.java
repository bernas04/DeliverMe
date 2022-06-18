package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.StoreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    
    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreRepository repository;


    @Test
    public void whenThereIsNoStores(){
        List<Store> emptyList = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(emptyList);
        List<Store> returned = storeService.getStores();
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
        assertThat(returned).isNotNull().isEmpty();
    }

    @Test
    public void whenSaveStoreReturIt(){
        Store s = new Store("Loja da Esquina", 
        new Address("Rua da Pega", "Aveiro", "Portugal", "3800"));
        Mockito.when(repository.save(s)).thenReturn(s);

        Store fromDB = storeService.saveStore(s);

        Mockito.verify(repository, VerificationModeFactory.times(1)).save(s);

        assertThat(s).isEqualTo(fromDB);
    }

    @Test
    public void whenGetVariousStores(){
        Store s1 = new Store("Loja das meias", new Address("Rua da Estia", "Campia", "PT", "3670"));
        Store s2 = new Store("Loja de Música", new Address("Rua da Velhice", "Ovar", "PT", "3800"));
        Store s3 = new Store("Loja da esquina",new Address("Rua da fonte", "Mação", "PT", "3819"));

        List<Store> allStores = new ArrayList<>();
        allStores.add(s1);
        allStores.add(s2);
        allStores.add(s3);

        Mockito.when(repository.findAll()).thenReturn(allStores);

        List<Store> fromDB = storeService.getStores();

        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();

        assertThat(fromDB).hasSize(allStores.size()).extracting(Store::getAddress)
                                                    .extracting(Address::getCity)
                                                    .containsExactly(s1.getAddress().getCity(), s2.getAddress().getCity(), s3.getAddress().getCity());
    }

}
