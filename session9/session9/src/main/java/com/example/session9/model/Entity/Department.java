package com.example.session9.model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.util.List;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;
}
