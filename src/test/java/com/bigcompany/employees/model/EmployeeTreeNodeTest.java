package com.bigcompany.employees.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTreeNodeTest {
    
    private EmployeeTreeNode root;
    private EmployeeTreeNode child1;
    private EmployeeTreeNode child2;
    
    @BeforeEach
    void setUp() {
        // Initialize root 
        Employee employeeCeo = new Employee(0, "matheus", "souza", 60000, null);
        root = new EmployeeTreeNode(employeeCeo);
        
        // Adding two child to 1st level CEO
        Employee employee1 = new Employee(1, "john", "silva", 45000, 0);
        child1 = new EmployeeTreeNode(employee1);
        Employee employee2 = new Employee(2, "joana", "specter", 47000, 0);
        child2 = new EmployeeTreeNode(employee2);
        root.addChild(child1);
        root.addChild(child2);
    }
    
    @Test
    void testAddChild() {
        // Verify that the root node has the correct children
        assertEquals(2, root.getChildren().size(), "Root should have 2 children");
        assertTrue(root.getChildren().contains(child1), "Root should contain child1");
        assertTrue(root.getChildren().contains(child2), "Root should contain child2");

        // Verify the parent relationship
        assertEquals(root, child1.getParent(), "Child1's parent should be root");
        assertEquals(root, child2.getParent(), "Child2's parent should be root");
    }
    
    @Test
    void testCalculateAverageSubordinateSalary() {
        // Calculate the average salary of direct subordinates of the root node
        double averageSubordinateSalary = root.calculateChildrenAverageSalary();
        assertEquals(46000, averageSubordinateSalary, 0.001, "Average salary of direct subordinates should be 46000");

        // Verify that a node with no children throws error, as this operation shouldn't even be made
        Exception exception = assertThrows(ArithmeticException.class, child1::calculateChildrenAverageSalary);
        String expectedMsg = "Cannot calculate average of node with no children";
        assertEquals(expectedMsg, exception.getMessage());
    }
    
}
