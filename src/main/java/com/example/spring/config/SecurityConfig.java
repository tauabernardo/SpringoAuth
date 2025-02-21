package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.spring.service.UserService;

@Configuration
public class SecurityConfig {

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService(UserService userService) {
        return userRequest -> {
            OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
            String githubId = oAuth2User.getAttribute("id");
            String name = oAuth2User.getAttribute("name");
            String email = oAuth2User.getAttribute("email");

            userService.saveUser(githubId, name, email);

            return oAuth2User;
        };
    }
}
