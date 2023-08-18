package com.management.HRMS.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.management.HRMS.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
