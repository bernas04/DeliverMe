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
import com.deliverMe.tqs.model.OrderStatus;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.PurchaseRepository;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {
    
    private Rider rider = new Rider("Joaquim", "29-04-2001");
    private Address address = new Address("Rua das Pombas", "Aveiro", "Portugal", "3800");
    private Store store = new Store("BookShelf", this.address);
    private Address purchaseAddress = new Address("Rua do Magistério Primário", "Aveiro", "Portugal", "3800");
    private Purchase purchase = new Purchase(this.store, "Zé", this.purchaseAddress);

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    public void whenThereIsNoPurchases(){
        List<Purchase> value = new ArrayList<>();
        Mockito.when(purchaseRepository.findAll()).thenReturn(value);

        List<Purchase> allPurchases = purchaseService.getAllPurchases();

        Mockito.verify(purchaseRepository, VerificationModeFactory.times(1)).findAll();

        assertThat(allPurchases).isNotNull().isEmpty();
        assertThat(allPurchases).isEqualTo(value);
    }

    @Test
    public void whenAllPurchasesAreInProgressShouldHaveARider(){
        List<Purchase> tmp = new ArrayList<>();
        tmp.add(this.purchase);
        Mockito.when(purchaseRepository.findAll()).thenReturn(tmp);

        this.purchase.setStatusInProgress(this.rider);

        List<Purchase> allInProgressPurchases = purchaseService.getInProgressPurchase();
        for (Purchase p : allInProgressPurchases){
            assertThat(p.getStatus()).isEqualTo(OrderStatus.IN_PROGRESS);
            assertThat(p).extracting(Purchase::getRider).extracting(Rider::getName).isEqualTo(this.rider.getName());
        }
    }

    @Test
    public void whenAllPurchasesAreDelivered(){
        this.purchase.setStatusInProgress(this.rider);
        List<Purchase> tmp = new ArrayList<>();
        this.purchase.setStatusDelivered();
        tmp.add(this.purchase);
        Mockito.when(purchaseRepository.findAll()).thenReturn(tmp);
    

        List<Purchase> allDeliveredPurchases = purchaseService.getDeliveredPurchase();

        for (Purchase p : allDeliveredPurchases){
            assertThat(p.getStatus()).isEqualTo(OrderStatus.DELIVERED);
            assertThat(p).extracting(Purchase::getRider).extracting(Rider::getName).isEqualTo(this.rider.getName());
        }

        List<Purchase> tt = new ArrayList<>();
        tt.add(this.purchase);
        
        assertThat(allDeliveredPurchases).extracting(Purchase::getRider).extracting(Rider::getPurchases).hasSize(1).containsExactly(tt);
    }

    @Test
    public void whenPurchaseIsCanceled(){
        Purchase purchaseTest = new Purchase(new Store("Zé dos Cães", new Address("Rua da Estia", "Póvoa", "Portugal", "3800")), 
        "Maria", new Address("Rua da Estia", "Campia", "Portugal", "3800"));

        purchaseTest.setStatusCanceled();

        List<Purchase> tmp = new ArrayList<>();
        tmp.add(purchaseTest);
        Mockito.when(purchaseRepository.findAll()).thenReturn(tmp);

        List<Purchase> allDeliveredPurchases = purchaseService.getCanceledPurchase();

        for (Purchase p : allDeliveredPurchases){
            assertThat(p.getStatus()).isEqualTo(OrderStatus.CANCELED);
            assertThat(p).extracting(Purchase::getRider).isNull();
        }
    }
}
