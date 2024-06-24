package com.bigcompany.employees.service.impl;

import com.bigcompany.employees.model.EmployeeAnalysisResult;
import com.bigcompany.employees.model.EmployeeTreeNode;
import com.bigcompany.employees.service.IEmployeeService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.bigcompany.employees.constant.EmployeeConstants.*;

/**
 * Triggers tree generation and print analysis results based on the business rules.
 */
public class EmployeeService implements IEmployeeService {

    /**
     * Stores the observations made during analysis run.
     */
    public List<String> observationsList;
    
    @Override
    public void analyzeEmployees(String filePathFromResource) {
        // Building the tree
        EmployeeTreeNode root = null;
        EmployeeTreeGenerator employeeTreeGenerator = new EmployeeTreeGenerator();
        try {
            root = employeeTreeGenerator.buildEmployeeTreeFromFile(filePathFromResource);
        } catch (NullPointerException e) {
            System.err.println("Couldn't find employees.csv. Ensure it is under resources/data");
        } catch (IOException e) {
            System.err.println("Error when reading employees.csv file.");
        }
        
        if (root == null) {
            return;
        }
        
        // Analyzing each node tree
        this.observationsList = new ArrayList<>();
        analyzeNodesRecursively(root);
        
        // Printing to console
        observationsList.forEach(System.out::println);
    }

    /**
     * Traverse the node subtree recursively gathering the analysis results observations.
     *
     * @param node Current node being analyzed.
     */
    private void analyzeNodesRecursively(EmployeeTreeNode node) {
        boolean isManager = false;
        double maxSalary = 0;
        double minSalary = 0;

        if (!node.getChildren().isEmpty()) {
            // Set manager properties when node has child
            isManager = true;
            double childAvgSalary = node.calculateChildrenAverageSalary();
            maxSalary =  childAvgSalary * MAX_SALARY_DIFFERENCE_COEFFICIENT;
            minSalary = childAvgSalary * MIN_SALARY_DIFFERENCE_COEFFICIENT;
        }

        EmployeeAnalysisResult result = new EmployeeAnalysisResult(
                node.getEmployee(),
                node.getLevel(),
                MAX_REPORTING_LEVELS_TO_CEO,
                minSalary,
                maxSalary,
                isManager
        );

        if (!result.getObservations().isEmpty()) {
            this.observationsList.addAll(result.getObservations());
        }

        for (EmployeeTreeNode child : node.getChildren()) {
            analyzeNodesRecursively(child);
        }
    }
}
