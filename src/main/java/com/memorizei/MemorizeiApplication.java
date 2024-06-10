package com.memorizei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.annotation.ServletSecurity;

@SpringBootApplication
@ServletSecurity
public class MemorizeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemorizeiApplication.class, args);
    }

}
