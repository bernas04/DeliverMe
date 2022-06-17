package com.deliverMe.tqs.repository;

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

import com.deliverMe.tqs.model.Manager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ManagerRepositoryTest {
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
    private ManagerRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void getManagerInfo(){
        Manager m= new Manager("Bernas","admin" ,"2001-04-29", "email@ua.pt");
        
        entityManager.persistAndFlush(m);

        List<Manager> allManagers = repository.findAll();
        assertThat(allManagers).isNotEmpty().hasSize(1).extracting(Manager::getName).containsExactly(m.getName());
    }

    @Test
    public void findByInvalidId(){
        Manager m= new Manager("Bernas", "admin" ,"2001-04-29", "email@ua.pt");
        entityManager.persistAndFlush(m);
        Optional<Manager> repoManager = repository.findById(m.getId()+100L);
        assertThat(repoManager).isEmpty().isNotPresent();
    }

    @Test
    public void getEmptyList(){
        List<Manager> allManagers = repository.findAll();
        assertThat(allManagers).hasSize(0).isEmpty();
    }
}
