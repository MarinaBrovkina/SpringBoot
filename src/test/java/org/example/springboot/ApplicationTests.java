package org.example.springboot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Container
    static GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    static GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void testDevApp() {
        Integer devAppPort = devapp.getMappedPort(8080);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devAppPort, String.class);
        assertEquals("Hello from dev!", forEntity.getBody());
    }

    @Test
    void testProdApp() {
        Integer prodAppPort = devapp.getMappedPort(8081);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodAppPort, String.class);
        assertEquals("Hello from prod!", forEntity.getBody());
    }
}

