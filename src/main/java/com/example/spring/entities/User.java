package com.example.spring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "app_user")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String githubId;
    private String name;
    private String email;

    public User() {
    }

    public User(Long id, String githubId, String name, String email) {
        this.id = id;
        this.githubId = githubId;
        this.name = name;
        this.email = email;
    }
}
