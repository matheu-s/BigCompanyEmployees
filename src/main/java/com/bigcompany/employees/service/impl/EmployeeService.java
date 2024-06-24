package com.bigcompany.employees.service.impl;

import com.bigcompany.employees.model.EmployeeAnalysisResult;
import com.bigcompany.employees.model.EmployeeTreeNode;
import com.bigcompany.employees.service.IEmployeeService;

import java.io.*;
import java.util.List;

import static com.bigcompany.employees.constant.EmployeeConstants.*;

/**
 * Triggers tree generation and print analysis results based on the business rules.
 * 
 * @author matheu-s
 */
public class EmployeeService implements IEmployeeService {
    /**
     * Responsible for generating the employee hierarchy from CSV file.
     */
    EmployeeTreeGenerator employeeTreeGenerator;
    
    @Override
    public void analyzeEmployees() {
        String filePathFromResource = "/data/employees.csv";

        // Building the tree
        EmployeeTreeNode root = null;
        employeeTreeGenerator = new EmployeeTreeGenerator();
        try {
            root = employeeTreeGenerator.buildEmployeeTreeFromFile(filePathFromResource);
        } catch (NullPointerException e) {
            System.err.println("Couldn't find employees.csv. Ensure it is under resources/data" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error when reading employees.csv file. " + e.getMessage());
        }
        
        if (root == null) {
            return;
        }
        
        // Analyzing nodes according to requirements
        EmployeeAnalyzer employeeAnalyzer = new EmployeeAnalyzer(
                MAX_REPORTING_LEVELS_TO_CEO,
                MAX_SALARY_DIFFERENCE_COEFFICIENT,
                MIN_SALARY_DIFFERENCE_COEFFICIENT
        );
        List<String> resultList = employeeAnalyzer.analyzeTree(root);
        
        // Printing to console
        resultList.forEach(System.out::println);
    }
}
