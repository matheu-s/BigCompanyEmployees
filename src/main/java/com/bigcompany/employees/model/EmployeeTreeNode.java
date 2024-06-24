package com.bigcompany.employees.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in a hierarchical tree structure, where each node contains an Employee object.
 * Each node can have multiple children, representing subordinates in a company hierarchy.
 */
public class EmployeeTreeNode {
    
    /**
     * The employee data associated with this node.
     */
    private final Employee employee;

    /**
     * This parent node.
     */
    private EmployeeTreeNode parent;

    /**
     * List of the children nodes.
     */
    private List<EmployeeTreeNode> children;

    /**
     * The node level in relation to the tree hierarchy.
     */
    private int level;

    /**
     * Constructs an EmployeeTreeNode with the specified employee.
     *
     * @param employee The employee data associated with this node.
     */
    public EmployeeTreeNode(Employee employee) {
        this.employee = employee;
        this.children = new ArrayList<>();
    }
    
    /**
     * Iterates all direct child and calculates the average salary of them.
     * 
     * @return The average salary of the direct child 
     */
    public double calculateChildrenAverageSalary() {
        if (this.getChildren().isEmpty()) {
            throw new ArithmeticException("Cannot calculate average of node with no children");
        }
        
        double sum = 0;
        for (EmployeeTreeNode child : this.children) {
            sum += child.getEmployee().getSalary();
        }

        return sum / this.children.size();
    }

    /**
     * Adds a child EmployeeTreeNode to the list of children and set relationship.
     * 
     * @param node EmployeeTreeNode that will be added as child.
     */
    public void addChild(EmployeeTreeNode node) {
        this.children.add(node);
        node.setParent(this);
    }

    public Employee getEmployee() {
        return employee;
    }
    
    public EmployeeTreeNode getParent() {
        return parent;
    }

    public void setParent(EmployeeTreeNode parent) {
        this.parent = parent;
    }

    public List<EmployeeTreeNode> getChildren() {
        return children;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
