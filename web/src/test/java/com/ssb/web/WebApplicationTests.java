package com.ssb.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Spring Framework: " + org.springframework.core.SpringVersion.getVersion());
        System.out.println("Spring Boot: " + org.springframework.boot.SpringBootVersion.getVersion());
    }
}
