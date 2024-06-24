package com.bigcompany.employees.service;

import com.bigcompany.employees.model.EmployeeTreeNode;

import java.util.List;

public interface IEmployeeAnalyzer {
    /**
     * Iterates the nodes and gathers analysis observations.
     *
     * @param root Root node of employee tree.
     * @return     List of observations.
     */
    List<String> analyzeTree(EmployeeTreeNode root);
}
