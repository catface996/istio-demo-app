package com.example.istio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IstioApplication {

    public static void main(String[] args) {
        SpringApplication.run(IstioApplication.class, args);
    }

}
