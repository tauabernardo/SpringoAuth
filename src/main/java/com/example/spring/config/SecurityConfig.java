package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login").permitAll()  // Permite acesso à página inicial e de login
                        .requestMatchers("/public/**").permitAll()  // Rotas públicas
                        .anyRequest().authenticated() // Todas as outras requerem autenticação
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Define a página de login personalizada
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")) // Configuração do logout
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Permite sessões conforme necessário
                );

        return http.build();
    }
}
