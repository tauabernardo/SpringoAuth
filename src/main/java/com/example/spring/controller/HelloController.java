package com.example.spring.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoint Público!";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "Acesso Restrito! Precisa estar logado.";
    }
}