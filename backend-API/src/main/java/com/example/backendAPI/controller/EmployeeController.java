package com.example.backendAPI.controller;

import com.example.backendAPI.module.Employee;
import com.example.backendAPI.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    /***
     *
     * @return All Employees Data
     */
    @GetMapping("/employees")
    public List<Employee> getAll(){
        logger.info("<< EmployeeController << getAll()");
       return  employeeService.getAll();
    }

    /***
     *
     * @param employee
     * @return
     */
    @PostMapping(value = "/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        logger.info("<< EmployeeController << createEmployee");
        return employeeService.createEmployee(employee);
    }

    /***
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable  Long id){
        logger.info("<< EmployeeController << getEmployee By Id");
       Employee employee = employeeService.getById(id);
        logger.info("{} Employee Data By Id "+ id, employee);
       return ResponseEntity.ok(employee);
    }

    /***
     *
     * @param id
     * @param employee
     * @return
     */
    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable  Long id, @RequestBody Employee employee){
        logger.info("<< EmployeeController << updateEmployee");
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRecord(@PathVariable("id") Long id){
        logger.info("<< EmployeeController << deleteRecord");
        employeeService.deleteRecord(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
