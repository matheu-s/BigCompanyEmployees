package com.bigcompany.employees.model;

import java.util.ArrayList;

public class EmployeeAnalysisResult {
    public ArrayList<String> observations;
    
    private Employee employee;
    private int level;
    private int maxLevel;
    private double maxSalary;
    private double minSalary;

    public EmployeeAnalysisResult(Employee employee, int level, int maxLevel, double maxSalary, double minSalary) {
        this.observations = new ArrayList<>(2);
        this.employee = employee;
        this.level = level;
        this.maxLevel = maxLevel;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        
        analyzeSalary();
        analyzeReportLine();
    }

    public void analyzeSalary() {
        double salary = employee.getSalary();
        double overpayment = salary - maxSalary;
        double underpayment = minSalary - salary;
        
        if (overpayment > 0) {
            String obs = "Employee " + employee.getId() + " is earning " + overpayment + " more than allowed.";
            observations.add(obs);
        } else if (underpayment > 0) {
            String obs = "Employee " + employee.getId() + " is earning " + underpayment +  " less than allowed.";
            observations.add(obs);
        }
    }
    
    private void analyzeReportLine() {
        if (employee.getManagerId() == null) {
            //Skip CEO
            return;
        }
        
        int levelDifference = maxLevel - level;
        if (levelDifference > 0) {
            String obs = String.format(
                    "Employee %d has a reporting line too big. Excess Levels: %d",
                    employee.getId(), levelDifference);
            observations.add(obs);
        }
    }

    public ArrayList<String> getObservations() {
        return observations;
    }
}
