package com.bigcompany.employees.service;

/**
 * Interface for Employee service layer.
 *
 * @author matheu-s
 */
public interface IEmployeeService {
    
    /**
     * Entry point function that gathers business requirements,
     * triggers tree generation and print results.
     * 
     * @param filePathFromResource Path to file in resources folder.
     */
    void analyzeEmployees(String filePathFromResource);
}
