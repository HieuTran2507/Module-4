package com.example.session9.Repository;

import com.example.session9.model.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
