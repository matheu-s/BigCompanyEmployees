package com.bigcompany.employees.service;

import com.bigcompany.employees.model.EmployeeTreeNode;

import java.io.IOException;

/**
 * Interface for Employee tree generation.
 */
public interface IEmployeeTreeGenerator {
    
    /**
     * Reads the file in the path and builds an employee hierarchy tree from it.
     *
     * @param csvResourceFilePath   Path of the CSV file in resources to generate the tree from.
     * @return                      The root EmployeeTreeNode, the CEO.
     * @throws NullPointerException When can't find CSV file.
     * @throws IOException          When can't read file.
     */
    EmployeeTreeNode buildEmployeeTreeFromFile(String csvResourceFilePath) throws IOException, NullPointerException;
}
