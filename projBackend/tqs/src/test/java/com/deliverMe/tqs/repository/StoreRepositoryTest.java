package com.deliverMe.tqs.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.Store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StoreRepositoryTest {
    @Container
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:11.12").withUsername("postgres")
                                                                             .withPassword("root")
                                                                             .withDatabaseName("deliverme");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private StoreRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    public void addStoresAndGetInfoAboutThem(){
        Address a = new Address("Rua Central", "Viseu", "Portugal", "3800");
        Address a1 = new Address("Rua Central", "Aveiro", "Portugal", "3600");

        manager.persistAndFlush(a);
        manager.persistAndFlush(a1);

        Store s = new Store("Loja das Meias", a);
        Store s1 = new Store("Loja das Bicicletas", a1);
        
        manager.persistAndFlush(s);
        manager.persistAndFlush(s1);

        assertThat(repository.findAll()).extracting(Store :: getAddress).extracting(Address::getCity).contains(s.getAddress().getCity(), s1.getAddress().getCity());
        assertFalse(repository.findById(s.getId()).equals(repository.findById(s1.getId())));
    
        Optional<Store> fromRepo = repository.findById(s.getId());
        Optional<Store> fromRepo1 = repository.findById(s1.getId());
        
        // se as lojas têm a mesma cidade, terão o mesmo zipcode
        if (fromRepo.get().getAddress().getCity().toLowerCase().equals(fromRepo1.get().getAddress().getCity().toLowerCase())){
            assertEquals(fromRepo.get().getAddress().getZipcode(), fromRepo1.get().getAddress().getZipcode());
        }
        else{
            // se não tiverem a mesma cidade, então o zipcode é diferente
            assertNotEquals(fromRepo.get().getAddress().getZipcode(), fromRepo1.get().getAddress().getZipcode());
        }
    }

    @Test
    public void findByInvalidId(){
        Address a = new Address("Rua Central", "Viseu", "Portugal", "3800");
        manager.persistAndFlush(a);
        
        Store s = new Store("Loja das Meias", a);
        manager.persistAndFlush(s);

        Optional<Store> storeFromRepo = repository.findById(-1L);

        assertThat(storeFromRepo).isEmpty().isNotNull();

    }
}
