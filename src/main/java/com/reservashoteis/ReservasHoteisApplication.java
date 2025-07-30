package com.reservashoteis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservasHoteisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservasHoteisApplication.class, args);
        System.out.println("Aplicação rodando em: http://localhost:8080");
    }
}
