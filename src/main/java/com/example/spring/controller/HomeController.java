package com.example.spring.controller;

import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    private final WebClient webClient;

    public HomeController() {
        this.webClient = WebClient.create("https://api.github.com");
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            // Obtém os atributos do usuário
            String name = principal.getAttribute("name");
            String githubId = principal.getAttribute("login");
            String avatarUrl = principal.getAttribute("avatar_url");

            // Obtém o email do GitHub (pode ser nulo)
            String email = principal.getAttribute("email");

            // Se o email não estiver disponível, tente obtê-lo via API
            if (email == null) {
                email = getEmailFromGitHub(principal);
            }

            // Adiciona os atributos ao modelo
            model.addAttribute("name", name);
            model.addAttribute("email", email != null ? email : "Email não disponível");
            model.addAttribute("github_id", githubId);
            model.addAttribute("avatar_url", avatarUrl);

            // Salva o usuário no banco de dados
            userService.saveUser(githubId, name, email);
        }
        return "home"; // Retorna a página home

    }

    private String getEmailFromGitHub(OAuth2User principal) {
        String token = (String) principal.getAttribute("access_token"); // Certifique-se de que o token está disponível
        if (token == null) {
            return "Email não disponível";
        }

        return webClient.get()
                .uri("/user/emails")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna a página de login
    }
}