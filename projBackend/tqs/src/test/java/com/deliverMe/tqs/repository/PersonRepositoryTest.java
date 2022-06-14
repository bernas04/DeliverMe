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
import org.testcontainers.junit.jupiter.Testcontainers;

import com.deliverMe.tqs.model.Person;
import com.deliverMe.tqs.model.Rider;

import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {
    @Container
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:11.12")
            .withUsername("demo")
            .withPassword("demopw")
            .withDatabaseName("delivery");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private PersonRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    public void savePersonAndThenGetIt(){
        Person p1 = new Rider("Zé", "1998-07-14", "ze@ua.pt");
        Person p2 = new Rider("Maria", "2000-07-14", "maria@ua.pt");
        Person p3 = new Rider("Antonio", "2001-07-14", "antonio@ua.pt");

        manager.persistAndFlush(p1);
        manager.persistAndFlush(p2);
        manager.persistAndFlush(p3);

        List<Person> allPerson = repository.findAll();

        assertThat(allPerson)
                .isNotNull()
                .hasSize(3)
                .extracting(Person::getId)
                .contains(p1.getId(), p2.getId());
    }    

    @Test
    public void getEmptyList(){
        List<Person> allPerson = repository.findAll();
        assertThat(allPerson).isNotNull().isEmpty();
    }

    @Test
    public void findById(){
        Person p1 = new Rider("Zé", "1998-07-14", "ze@ua.pt");
        manager.persistAndFlush(p1);

        Optional<Person> person = repository.findById(p1.getId());
        assertThat(person).isPresent().isNotNull();

        assertThat(person.get()).isEqualTo(p1);


    }
    
    @Test
    public void findByInvalidId(){
        Person p1 = new Rider("Zé", "1998-07-14", "ze@ua.pt");
        manager.persistAndFlush(p1);

        Optional<Person> person = repository.findById(-1L);
        assertThat(person).isNotPresent().isNotNull();

    }

    @Test
    public void findByEmail(){
        Person p1 = new Rider("Zé", "1998-07-14", "ze@ua.pt");
        manager.persistAndFlush(p1);

        Optional<Person> person = repository.findByEmail(p1.getEmail());
        assertThat(person).isPresent().isNotNull();

        assertThat(person.get()).isEqualTo(p1);
    }

    @Test
    public void findByInvalidEmail(){
        Person p1 = new Rider("Zé", "1998-07-14", "ze@ua.pt");
        manager.persistAndFlush(p1);

        Optional<Person> person = repository.findByEmail(p1.getEmail()+"p");
        assertThat(person).isNotPresent().isNotNull();
    }

}
