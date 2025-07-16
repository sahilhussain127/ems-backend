package net.javaguides.ems_backend.repository;

import net.javaguides.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailAndPassword(String email, String password);
}
