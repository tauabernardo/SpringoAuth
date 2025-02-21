package com.example.spring.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping("/")
    public String home(@AuthenticationPrincipal Object principal, Model model) {
        // Verifica se o usuário está autenticado
        if (principal != null) {
            model.addAttribute("name", principal.toString()); // Adiciona o nome do usuário ao modelo
            model.addAttribute("avatar_url", "URL_DO_AVATAR"); // Substitua pela URL real do avatar
        }
        return "home"; // Retorna a página home
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna a página de login
    }
}