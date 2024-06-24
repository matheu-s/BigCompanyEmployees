package com.bigcompany.employees.service;

import com.bigcompany.employees.model.EmployeeAnalysisResult;
import com.bigcompany.employees.model.EmployeeTreeNode;

import java.io.IOException;
import java.util.List;

public interface IEmployeeService {
    
    /**
     * Entry point function; It builds employee's structure 
     * and analyzes each employee based on the rules of salary
     * threshold and report line limit.
     */
    void analyzeEmployees();

//    /**
//     * Check if salary respects the thresholds set and report possible discrepancy
//     * 
//     * @param node Employee node under analysis
//     * @return An analysis result with improvement suggestions
//     */
//    void analyzeNode(EmployeeTreeNode node, int maxLevels, double maxSalary, double minSalary, List<EmployeeAnalysisResult> analysisList);

//    /**
//     * Check if report line length is under the limit and report possible discrepancy
//     * 
//     * @param node Employee node under analysis
//     * @return An analysis result with improvement suggestions
//     */
//    void analyzeEmployeeReportLine(EmployeeTreeNode node);
}
