package com.bigcompany.employees.service.impl;

import com.bigcompany.employees.model.Employee;
import com.bigcompany.employees.model.EmployeeAnalysisResult;
import com.bigcompany.employees.model.EmployeeTreeNode;
import com.bigcompany.employees.service.IEmployeeService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.bigcompany.employees.constant.EmployeeConstants.*;

/**
 * Handles tree generation and analysis results based on the business rules.
 * 
 * @author matheu-s
 */
public class EmployeeService implements IEmployeeService {
    
    @Override
    public void analyzeEmployees() {
        // Building the tree
        EmployeeTreeNode root = null;
        try {
            root = buildEmployeeTreeFromFile();
        } catch (NullPointerException e) {
            System.err.println("Couldn't find employees.csv. Ensure it is under resources/data" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error when reading employees.csv file. " + e.getMessage());
        }
        
        if (root == null) {
            return;
        }
        
        setTreeLevels(root);
        
        // Analyzing nodes and outputting observations to console 
        analyzeNodesRecursively(
                root, 
                MAX_REPORTING_LEVELS_TO_CEO, 
                MAX_SALARY_DIFFERENCE_COEFFICIENT,
                MIN_SALARY_DIFFERENCE_COEFFICIENT
        );
    }

    /**
     * Analyzes all nodes and outputs observations to console recursively
     * 
     * @param node Current node being analyzes
     * @param maxLevels Maximum levels allowed between node and root
     * @param maxSalaryCoefficient Maximum salary coefficient allowed between manager and subordinates
     * @param minSalaryCoefficient Minimum salary coefficient allowed between manager and subordinates
     */
    private void analyzeNodesRecursively(EmployeeTreeNode node, int maxLevels, double maxSalaryCoefficient, double minSalaryCoefficient) {
        double childAvgSalary = node.calculateChildrenAverageSalary();
        double maxSalary =  childAvgSalary * maxSalaryCoefficient;
        double minSalary = childAvgSalary * minSalaryCoefficient;
        
        EmployeeAnalysisResult result = new EmployeeAnalysisResult(
                node.getEmployee(),
                node.getLevel(),
                maxLevels,
                minSalary,
                maxSalary
        );
        
        // Printing observations to console
        result.getObservations().forEach(System.out::println);
        
        // Recursive analysis
        for (EmployeeTreeNode child : node.getChildren()) {
            analyzeNodesRecursively(
                    child, 
                    maxLevels,
                    maxSalaryCoefficient,
                    minSalaryCoefficient
            );
        }
    }
    
    /**
     * Reads resources/data/employees.csv and builds an employee hierarchy tree
     *
     * @return The root EmployeeTreeNode, the CEO
     * @throws NullPointerException When can't find CSV file
     * @throws IOException When can't read file
     */
    private EmployeeTreeNode buildEmployeeTreeFromFile() throws IOException, NullPointerException {
        // A map to store EmployeeTreeNode objects by employee ID for quick access
        Map<Integer, EmployeeTreeNode> nodeMap = new HashMap<>();
        
        EmployeeTreeNode root = null;
        
        try (        
                InputStream inputStream = getClass().getResourceAsStream("/data/employees.csv");
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
