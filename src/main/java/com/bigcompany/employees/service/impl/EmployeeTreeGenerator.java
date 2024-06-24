package com.bigcompany.employees.service.impl;

import com.bigcompany.employees.model.Employee;
import com.bigcompany.employees.model.EmployeeTreeNode;
import com.bigcompany.employees.service.IEmployeeAnalyzer;
import com.bigcompany.employees.service.IEmployeeTreeGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Generates the employee hierarchy tree.
 */
public class EmployeeTreeGenerator implements IEmployeeTreeGenerator {
    
    @Override
    public EmployeeTreeNode buildEmployeeTreeFromFile(String csvResourceFilePath) throws IOException, NullPointerException {
        // A map to store EmployeeTreeNode objects by employee ID for quick access
        Map<Integer, EmployeeTreeNode> nodeMap = new HashMap<>();

        EmployeeTreeNode root = null;

        try (
                InputStream inputStream = getClass().getResourceAsStream(csvResourceFilePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            reader.readLine(); // Skip the header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Parsing data from line
                int id =  Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName  = data[2];
                double salary = Double.parseDouble(data[3]);
                Integer managerId = null;
                if (data.length == 5) {
                    managerId = Integer.valueOf(data[4]);
                }

                // Creating objects from data
                EmployeeTreeNode currentNode = nodeMap.get(id);
                if (currentNode == null) {
                    Employee employee = new Employee(id, firstName, lastName, salary, managerId);
                    currentNode = new EmployeeTreeNode(employee);
                    nodeMap.put(id, currentNode);
                } else {
                    // Updating, if it was created before (discovered by subordinate)
                    currentNode.getEmployee().setFirstName(firstName);
                    currentNode.getEmployee().setLastName(lastName);
                    currentNode.getEmployee().setSalary(salary);
                    currentNode.getEmployee().setManagerId(managerId);
                }

                if (managerId == null) {
                    // Found CEO, set as root
                    currentNode.setLevel(0);
                    root = currentNode;
                } else {
                    // Setting parent/manager relation
                    EmployeeTreeNode managerNode = nodeMap.get(managerId);
                    if (managerNode == null) {
                        managerNode = new EmployeeTreeNode(new Employee(managerId, "", "", 0, null));
                        nodeMap.put(managerId, managerNode);
                    }
                    managerNode.addChild(currentNode);
                }
            }
        }

        if (root != null) {
            setTreeLevels(root);
        }

        return root;
    }

    /**
     * Recursively sets the Level property for all the nodes in the subtree.
     *
     * @param node The current EmployeeTreeNode
     */
    private void setTreeLevels(EmployeeTreeNode node) {
        for (EmployeeTreeNode child : node.getChildren()) {
            child.setLevel(node.getLevel() + 1);
            setTreeLevels(child);
        }
    }
}
