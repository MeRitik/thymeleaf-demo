package com.thymeleaf.cruddemo.service;

import com.thymeleaf.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

    public List<Employee> findAllByOrderByFirstNameAsc();
}
