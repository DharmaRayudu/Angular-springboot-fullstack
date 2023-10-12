package com.example.backendAPI.service.impl;

import com.example.backendAPI.exception.ResourceNotFoundException;
import com.example.backendAPI.module.Employee;
import com.example.backendAPI.repository.EmployeeRepository;
import com.example.backendAPI.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("<<EmployeeImpl << createEmployee");
        logger.info("{} Employee data", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        logger.info("<<EmployeeImpl << getById");
        logger.info("{} Employee data By id", id);
        return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Id not found"+ id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        logger.info("<<EmployeeImpl << updateEmployee By Id ", id);
        Employee employeeData =  employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Id not found "+ id));

        if(employee.getFirstName() != null){
            employeeData.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null){
            employeeData.setLastName(employee.getLastName());
        }
        if(employee.getEmailId() !=null){
            employeeData.setEmailId(employee.getEmailId());
        }

        return employeeRepository.save(employeeData);
    }

    @Override
    public void deleteRecord(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Id not found "+ id));
        employeeRepository.deleteById(id);
    }
}
