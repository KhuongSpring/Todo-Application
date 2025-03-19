package com.example.pet_project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name_task")
    String taskName ;

    @Column(name = "completed")
    boolean completed;

    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
}
