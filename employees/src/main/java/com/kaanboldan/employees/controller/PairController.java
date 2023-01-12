package com.kaanboldan.employees.controller;

import com.kaanboldan.employees.model.Employees;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kaanboldan.employees.api.PairApi.*;

@RestController
public class PairController {
    @GetMapping("/deneme")
    public String isWorking() {
        return "Hello, I'm working.";
    }

    @GetMapping(value = "/getPair/{filePath}")
    public StringBuilder getUserViaFilePath(@PathVariable("filePath") String filePath){
        List<Employees> employeesList = getEmployeesList(filePath);
        return checkConflict(employeesList);
    }

    @GetMapping(value = "/getPair")
    public StringBuilder getUser(){
        String filePath="C:\\Users\\kaanboldan\\IdeaProjects\\sirmaSolution1\\file.csv";
        List<Employees> employeesList = getEmployeesList(filePath);
        return checkConflict(employeesList);
    }
}
