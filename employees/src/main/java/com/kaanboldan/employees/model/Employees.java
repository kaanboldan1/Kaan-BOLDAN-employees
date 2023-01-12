package com.kaanboldan.employees.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;


@JsonPropertyOrder({"EmpID", "ProjectID", "DateFrom", "DateTo"})
public class Employees {

    String empID;
    String projectID;
    Date dateFrom;
    Date dateTo;


    public String getEmpID() {
        return empID;
    }
    public String getProjectID() {return projectID;}
    public void setEmpID(String empID) {
        this.empID = empID;
    }
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }



}

