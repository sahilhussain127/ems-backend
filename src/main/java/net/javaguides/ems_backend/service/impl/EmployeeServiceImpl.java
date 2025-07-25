package net.javaguides.ems_backend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;
import net.javaguides.ems_backend.mapper.EmployeeMapper;
import net.javaguides.ems_backend.repository.EmployeeRepository;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {


        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
            Employee savedEmployee=    employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto((savedEmployee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
      Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto loginEmployee(String email, String password) {
        Employee employee = employeeRepository
                .findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }



    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exisit with given id: " + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setPassword(updatedEmployee.getPassword());

      Employee updatedEmployeeObj =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Cannot delete Employee is not exisit with given id: " + employeeId)
        );
employeeRepository.deleteById(employeeId);
    }
}
