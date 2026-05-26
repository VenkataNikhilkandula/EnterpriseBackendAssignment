package com.enterpriseassignment.entity;

import com.enterpriseassignment.audit.AuditEntity;
import com.enterpriseassignment.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    
    @OneToMany(mappedBy = "createdBy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Project> projects;

    @OneToMany(mappedBy = "assignedUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Task> assignedTasks;

    
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<TaskComment> comments;

    
    @OneToMany(mappedBy = "updatedBy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<TaskActivity> activities;
}