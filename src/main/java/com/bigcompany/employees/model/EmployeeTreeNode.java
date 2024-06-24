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
     * Iterates all direct child and calculates the average salary of them
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

    /**
     * Gathers all nodes in the subtree rooted at this node.
     *
     * @return A list of all nodes in this subtree, including the current node.
     */
    public List<EmployeeTreeNode> getAllNodes() {
        List<EmployeeTreeNode> allNodes = new ArrayList<>();
        collectChildNodesRecursively(this, allNodes);
        return allNodes;
    }

    /**
     * Adds nodes to the employee's list recursively.
     * 
     * @param node current node expanding.
     * @param allNodes initial empty list.
     */
    private void collectChildNodesRecursively(EmployeeTreeNode node, List<EmployeeTreeNode> allNodes) {
        allNodes.add(node);
        for (EmployeeTreeNode child : node.getChildren()) {
            System.out.println("Employee "+ child.employee.getId() + " on level " + child.getLevel() + " responds to " + child.getEmployee().getManagerId());
            collectChildNodesRecursively(child, allNodes);
        }
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
