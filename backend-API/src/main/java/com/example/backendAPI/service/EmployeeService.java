package com.example.backendAPI.service;

import com.example.backendAPI.module.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAll();

    public Employee createEmployee(Employee employee);

    public Employee getById(Long id);

    public Employee updateEmployee(Long id, Employee employee);

    public void deleteRecord(Long id);
}


