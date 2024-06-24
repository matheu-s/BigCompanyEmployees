package com.bigcompany.employees.service.impl;

import com.bigcompany.employees.model.EmployeeTreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class EmployeeTreeGeneratorTest {
    EmployeeTreeGenerator employeeTreeGenerator;
    EmployeeTreeNode root;
    
    @BeforeEach
    public void setUp() {
        employeeTreeGenerator = new EmployeeTreeGenerator();
    }
    
    @Test
    void testBuildEmployeeTreeFromFile() {
        try {
            root = employeeTreeGenerator.buildEmployeeTreeFromFile("/data/employees-test.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(root, "Root should not be null");
        assertEquals(123, root.getEmployee().getId(), "Root should have ID 123");
        assertEquals(4, root.getChildren().size(), "Root should have 4 children");

        // Check second level
        EmployeeTreeNode secondLevelManager = null;
        for (EmployeeTreeNode child : root.getChildren()) {
            if (child.getEmployee().getId() == 124) {
                secondLevelManager = child;
            }
        }
        assertNotNull(secondLevelManager);
        assertEquals(1, secondLevelManager.getChildren().size(), "Manager 124 should have one child");
        assertEquals(300, secondLevelManager.getChildren().getFirst().getEmployee().getId(), "124 child should be the ID 300");
    }    
}
