package net.javaguides.ems_backend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<EmployeeDto> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        EmployeeDto employeeDto = employeeService.loginEmployee(email, password);
        return ResponseEntity.ok(employeeDto);
    }


// Build Add Employee RES API
@PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
    EmployeeDto savedEmployee= employeeService.createEmployee(employeeDto);
    return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    // Build GET Employee REST API
@GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){

     EmployeeDto employeeDto =   employeeService.getEmployeeById(employeeId);
     return ResponseEntity.ok(employeeDto);
    }



    // Build Get All Employess REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
    List<EmployeeDto> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
    }



    // Build update employee REST API
@PutMapping("{id}")
    public  ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee)
    {
       EmployeeDto employeeDto = employeeService.updateEmployee(employeeId,updatedEmployee);
       return ResponseEntity.ok(employeeDto);
    }


    // Build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
    employeeService.deleteEmployee(employeeId);
    return  ResponseEntity.ok("Employee Deleted Successfully!");
    }
}
