package com.bigcompany.employees.service;

/**
 * Interface for Employee service layer.
 *
 * @author matheu-s
 */
public interface IEmployeeService {
    
    /**
     * Entry point function that builds the employee's structure 
     * and analyzes each employee based on the rules of salary
     * thresholds and report line limit set by the business.
     */
    void analyzeEmployees();
}
