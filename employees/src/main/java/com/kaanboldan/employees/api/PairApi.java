package com.kaanboldan.employees.api;

import com.kaanboldan.employees.model.Employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PairApi {


    //Parsing csv File to EmployeesModel
    public static List<Employees> getEmployeesList(String csvLocation) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(csvLocation));
            List<Employees> empList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                // split on comma(',')
                String[] personCsv = line.split(",");

                // create Employees object to store values
                Employees employeesModelObj = new Employees();

                // add values from csv to EmployeesModel object
                employeesModelObj.setEmpID(personCsv[0]);
                employeesModelObj.setProjectID(personCsv[1]);
                employeesModelObj.setDateFrom(getDate(personCsv[2]));
                employeesModelObj.setDateTo(getDate(personCsv[3]));

                empList.add(employeesModelObj);
            }
            return empList;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static java.util.Date getDate(String date) {
        //String to java.util.Date conversion
        date = date.trim();
        if (date.contains("NULL")) date = new Date(System.currentTimeMillis()).toString();

        try {
            LocalDate localeDate = LocalDate.parse(date);
            return getDate(localeDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static java.util.Date getDate(LocalDate date) {

        return Date.valueOf(date);
    }

    public static StringBuilder checkConflict(List<Employees> employeesList) {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < employeesList.size(); i++) {
            ArrayList<String> workwith = new ArrayList<>();
            workwith.add(employeesList.get(i).getEmpID());
            for (int j = 0; j < employeesList.size(); j++) {
                if (i == j) continue;
                //if employee worked long time than other employee
                if (employeesList.get(i).getDateFrom().after(employeesList.get(j).getDateFrom()) && employeesList.get(i).getDateTo().before(employeesList.get(j).getDateTo()) && employeesList.get(i).getProjectID().contains(employeesList.get(j).getProjectID())) {
                    workwith.add(employeesList.get(j).getEmpID());
                    long diff = employeesList.get(i).getDateFrom().getTime() - employeesList.get(j).getDateFrom().getTime();
                    long days = TimeUnit.MILLISECONDS.toDays(diff);
                    workwith.add("Project Id:" + employeesList.get(i).getProjectID());
                    workwith.add("Work with days:" + days);
                }
            }
            if (workwith.size() > 3) {
                StringBuilder with = new StringBuilder();
                for (int j = 0; j < workwith.size(); j++) {
                    if (j < workwith.size() - 1) with.append(workwith.get(j)).append(", ");
                    else with.append(workwith.get(j));
                }
                returnValue.append(with + "<br> ");
            }
        }
        return returnValue;
    }
}
