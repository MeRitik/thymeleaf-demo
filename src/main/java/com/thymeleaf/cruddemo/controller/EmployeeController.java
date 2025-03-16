package com.thymeleaf.cruddemo.controller;

import com.thymeleaf.cruddemo.entity.Employee;
import com.thymeleaf.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.findAllByOrderByFirstNameAsc();
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @PostMapping("/save")
    public String saveEmployeeForm(@ModelAttribute("employee") Employee employee
                                  , BindingResult result
    ) {
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/add-employee";
    }

    @GetMapping("/showFormForUpdate")
    public String updateForm(@RequestParam("employeeId") int employeeId, Model model) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "employees/update-employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list";
    }
}
