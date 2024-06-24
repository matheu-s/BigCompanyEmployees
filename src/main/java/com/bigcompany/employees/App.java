package com.bigcompany.employees;

import com.bigcompany.employees.service.impl.EmployeeService;

public class App 
{
    public static void main( String[] args ) {
        EmployeeService employeeService = new EmployeeService();
        String inputFile = "/data/employees.csv";
        employeeService.analyzeEmployees(inputFile);
    }
}
