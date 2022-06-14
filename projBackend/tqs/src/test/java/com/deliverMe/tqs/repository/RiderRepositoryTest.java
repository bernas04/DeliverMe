package com.deliverMe.tqs.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.deliverMe.tqs.model.Rider;

import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RiderRepositoryTest {
    
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
    private RiderRepository repository;

    @Autowired
    private TestEntityManager manager;


    @Test
    public void addVariousRidersAndGetInfoAboutThem(){
        Rider r1 = new Rider("João", "2000-04-12", "joao@ua.pt");
        Rider r2 = new Rider("Maria", "1999-01-18", "maria@ua.pt");
        Rider r3 = new Rider("António", "1998-04-28", "antonio@ua.pt");
        Rider r4 = new Rider("Matilde", "2000-05-20", "matilde@ua.pt");

        manager.persistAndFlush(r1);
        manager.persistAndFlush(r2);
        manager.persistAndFlush(r3);
        manager.persistAndFlush(r4);

        List<Rider> allRiders = repository.findAll();

        assertThat(allRiders).hasSize(4).extracting(Rider::getName).containsExactly(r1.getName(), r2.getName(), r3.getName(), r4.getName());
        allRiders.remove(0);
        allRiders.remove(0);
        allRiders.remove(0);

        assertThat(allRiders).extracting(Rider::getEmail).containsOnly(r4.getEmail());
    }

    @Test
    public void findByEmail(){
        Rider r = new Rider("Rui", "1999-05-18", "rui@ua.pt");
        manager.persistAndFlush(r);

        Optional<Rider> returnFromRepository = repository.findByEmail(r.getEmail());
        assertEquals(r.getName(), returnFromRepository.get().getName());
    }

    @Test
    public void findByInvalidEmail(){
        Rider r = new Rider("Tomás", "1998-09-19", "tomas@ua.pt");
        manager.persistAndFlush(r);

        Optional<Rider> fromRepo = repository.findByEmail(r.getEmail()+"t");
        
        assertThat(fromRepo).isEmpty().isNotPresent();
    }

    @Test
    public void findByInvalidId(){
        Rider r = new Rider("Tomás", "1998-09-19", "tomas@ua.pt");
        manager.persistAndFlush(r);

        Optional<Rider> fromRepo = repository.findById(1000L);

        assertThat(fromRepo).isEmpty().isNotPresent();
    }
}
