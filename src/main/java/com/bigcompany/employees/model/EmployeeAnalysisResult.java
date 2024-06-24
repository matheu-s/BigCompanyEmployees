package com.bigcompany.employees.model;

import java.util.ArrayList;

/**
 * Encapsulates analysis results based on passed data and thresholds.
 */
public class EmployeeAnalysisResult {
    
    /**
     * Stores the observations done on analysis methods
     */
    public ArrayList<String> observations;

    /**
     * The employee data to be analyzed.
     */
    private final Employee employee;

    /**
     * The employee level in the hierarchy tree.
     */
    private final int level;

    /**
     * Maximum report line length allowed.
     */
    private final int maxLevel;

    /**
     * Maximum salary allowed. 
     */
    private final double maxSalary;

    /**
     * Minimum salary allowed. 
     */
    private final double minSalary;

    /**
     * Constructs EmployeeAnalysisResult with defined data and run analysis methods.
     * 
     * @param employee  The employee data to be analyzed.
     * @param level     The employee level in the hierarchy tree.
     * @param maxLevel  Maximum report line length allowed.
     * @param maxSalary Maximum salary allowed. 
     * @param minSalary Minimum salary allowed. 
     */
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

    /**
     * Analyzes if payment is between the allowed range, 
     * if negative, adds observation to class informing the discrepancy amount.
     */
    private void analyzeSalary() {
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

    /**
     * Analyzes if report line length is under the limit,
     * if negative, adds observation to class informing the discrepancy amount.
     */
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

    /**
     * Gets the possible observation that were created during analysis.
     * 
     * @return Observations list
     */
    public ArrayList<String> getObservations() {
        return observations;
    }
}
