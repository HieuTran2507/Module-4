package com.example.session10.model.entity;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private String email;

    private String department;

    private String avatarUrl;

}
