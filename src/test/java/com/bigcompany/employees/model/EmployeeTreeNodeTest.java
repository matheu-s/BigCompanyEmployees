package com.bigcompany.employees.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeTreeNodeTest {
    
    private EmployeeTreeNode root;
    private EmployeeTreeNode child1;
    private EmployeeTreeNode child2;
    
    @BeforeEach
    void setUp() {
        System.out.println("setting up");
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
        double averageSubordinateSalary = root.calculateSubordinatesAverageSalary();
        assertEquals(46000, averageSubordinateSalary, 0.001, "Average salary of direct subordinates should be 46000");

        // Verify that a node with no children returns NaN as the average salary, as this operation shouldn't even be made
        double child1AverageSalary = child1.calculateSubordinatesAverageSalary();
        assertEquals(Double.NaN, child1AverageSalary, "Average salary of direct subordinates for child1 should be NaN");
    } 
    
    @Test
    void testGetAllNodes() {
        // Get all nodes in the tree starting from the root
        List<EmployeeTreeNode> allNodes = root.getAllNodes();
        assertEquals(3, allNodes.size(), "There should be 3 nodes in total (root + 2 children)");

        // Verify that all expected nodes are present in the list
        assertTrue(allNodes.contains(root), "All nodes should contain root");
        assertTrue(allNodes.contains(child1), "All nodes should contain child1");
        assertTrue(allNodes.contains(child2), "All nodes should contain child2");
    }
    
}
