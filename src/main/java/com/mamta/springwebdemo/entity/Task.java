package com.mamta.springwebdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TABLE_TASK")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TaskStatus status;

    private String priority;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
