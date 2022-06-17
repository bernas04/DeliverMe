package com.deliverMe.tqs.repository;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.OrderStatus;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.model.Store;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PurchaseRepositoryTest {
    @Container
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:11.12").withUsername("postgres")
                                                                             .withPassword("root")
                                                                             .withDatabaseName("deliverme");



    /* spring.datasource.url=jdbc:postgresql://localhost:5432/deliverme
    spring.datasource.username=postgres
    spring.datasource.password=root */

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    public void createAndGetPurchaseInfo(){
        Address a = new Address("Rua Central", "Aveiro", "Portugal", "3800");
        Store s = new Store("Loja das Meias", a, 80.0, 10.0);
        Purchase p = new Purchase(s, "Maria", a);

        manager.persist(a);
        manager.persist(s);
        manager.persistAndFlush(p);

        Optional<Purchase> returned = repository.findById(p.getId());
        assertThat(returned).isPresent().contains(p);
    }

    @Test
    public void findByInvalidId(){
        Address a = new Address("Rua Central", "Aveiro", "Portugal", "3800");
        Store s = new Store("Loja das Meias", a, 80.0, 10.0);
        Purchase p = new Purchase(s, "Maria", a);
        
        manager.persist(a);
        manager.persist(s);
        manager.persistAndFlush(p);

        Optional<Purchase> returned = repository.findById(-1L);
        assertThat(returned).isNotPresent().isNotEqualTo(p);
    }

    @Test
    public void findByWhenIsEmpty(){
        List<Purchase> returned = repository.findAll();
        assertThat(returned).hasSize(0).isEmpty();
    }

    @Test
    public void riderAssociatedToOrderAndStatusChange(){
        Address a = new Address("Rua Central", "Aveiro", "Portugal", "3800");
        Store s = new Store("Loja das Meias", a, 80.0, 10.0);
        Purchase p = new Purchase(s, "Maria", a);
        Rider r = new Rider("Zé", "admin","29-04-2001", "ze@ua.pt");


        manager.persist(a);
        manager.persist(s);
        manager.persistAndFlush(r);
        manager.persistAndFlush(p);

        Optional<Purchase> purchaseFromRepository = repository.findById(p.getId());

        purchaseFromRepository.get().setRider(r);

        assertThat(purchaseFromRepository).contains(p);
        assertEquals(purchaseFromRepository.get().getStatus(), OrderStatus.IN_PROGRESS);

        purchaseFromRepository.get().setStatusDelivered();
        assertEquals(purchaseFromRepository.get().getStatus(), OrderStatus.DELIVERED);

    }


}
