package com.kaanboldan.employees.controller;

import com.kaanboldan.employees.model.Employees;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kaanboldan.employees.api.PairApi.*;

@RestController
public class PairController {
    @GetMapping("/test")
    public String isWorking() {
        return "Hello, I'm working.";
    }
    @GetMapping(value = "/getpair")
    public StringBuilder getUser(){
        String filePath="src/main/java/com/kaanboldan/employees/file.csv";
        List<Employees> employeesList = getEmployeesList(filePath);
        return checkConflict(employeesList);
    }
}
