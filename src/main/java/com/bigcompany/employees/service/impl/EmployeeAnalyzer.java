package com.bigcompany.employees.service.impl;

import com.bigcompany.employees.model.EmployeeAnalysisResult;
import com.bigcompany.employees.model.EmployeeTreeNode;
import com.bigcompany.employees.service.IEmployeeAnalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyzes the employee tree nodes.
 */
public class EmployeeAnalyzer implements IEmployeeAnalyzer {
    
    /**
     * List of analysis observations.
     */
    ArrayList<String> observationsList;
    
    /**
     * Maximum report line length allowed.
     */
    int maxLevels;

    /**
     * Maximum salary coefficient. 
     */
    double maxSalaryCoefficient;

    /**
     * Minimum salary coefficient. 
     */
    double minSalaryCoefficient;

    /**
     * Constructs the EmployeeAnalyzer according to passed requirements.
     * 
     * @param maxLevels           Maximum report line length allowed.
     * @param maxSalaryCoefficient Maximum salary coefficient. 
     * @param minSalaryCoefficient Minimum salary coefficient. 
     */
    public EmployeeAnalyzer(int maxLevels, double maxSalaryCoefficient, double minSalaryCoefficient) {
        this.observationsList = new ArrayList<>();
        this.maxLevels = maxLevels;
        this.maxSalaryCoefficient = maxSalaryCoefficient;
        this.minSalaryCoefficient = minSalaryCoefficient;
    }

    @Override
    public List<String> analyzeTree(EmployeeTreeNode root) {
        // Recursive analysis
        analyzeNodesRecursively(root);
        
        return observationsList;
    }

    /**
     * Traverse the node subtree recursively adding the analysis results observations.
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
            maxSalary =  childAvgSalary * maxSalaryCoefficient;
            minSalary = childAvgSalary * minSalaryCoefficient;
        }

        EmployeeAnalysisResult result = new EmployeeAnalysisResult(
                node.getEmployee(),
                node.getLevel(),
                maxLevels,
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
