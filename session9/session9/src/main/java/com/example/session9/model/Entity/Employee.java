package com.example.session9.model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String email;
    private String phone;
    private Double salary;
    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
