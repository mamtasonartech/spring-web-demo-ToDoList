package com.mamta.springwebdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id ;
    private String username;
    private String password;
    private String email;

    @Column(name = "created_at")
    private Instant createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

}
