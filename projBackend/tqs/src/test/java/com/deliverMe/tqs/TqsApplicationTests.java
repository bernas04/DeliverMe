package com.deliverMe.tqs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class TqsApplicationTests {

	@Container
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:11.12")
																			 .withUsername("postgres")
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

	@Test
	void contextLoads() {
	}

}
