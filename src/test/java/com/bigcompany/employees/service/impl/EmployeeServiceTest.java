package com.bigcompany.employees.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService;
    
    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }
    
    @Test
    void testAnalyzeEmployeesWithValidInput() {
        String inputFile = "/data/employees-test.csv";
        employeeService.analyzeEmployees(inputFile);
        List<String> observations = employeeService.observationsList;

        assertFalse(observations.isEmpty(), "Results should not be empty");
        assertTrue(observations.contains("Employee 407 has a reporting line too big. Excess Levels: 3"), "Results should contain the analysis");
    }

    @Test
    void testAnalyzeEmployeesWithEmptyCsv() {
        String inputFile = "/data/wrong-employees-test.csv";
        employeeService.analyzeEmployees(inputFile);
        List<String> observations = employeeService.observationsList;
        
        assertNull(observations);
    }

    @Test
    void testAnalyzeEmployeesResults() {
        String inputFile = "/data/employees-test.csv";
        employeeService.analyzeEmployees(inputFile);
        List<String> observations = employeeService.observationsList;

        assertFalse(observations.isEmpty(), "Results should not be empty");

        // Checking report line obs
        assertTrue(observations.contains("Employee 407 has a reporting line too big. Excess Levels: 3"), "Results should contain the analysis");
        assertTrue(observations.contains("Employee 406 has a reporting line too big. Excess Levels: 2"), "Results should contain the analysis");
        assertTrue(observations.contains("Employee 405 has a reporting line too big. Excess Levels: 1"), "Results should contain the analysis");
        assertFalse(observations.contains("Employee 404 has a reporting line too big. Excess Levels: 0"), "Results not should contain this wrong analysis");
        assertFalse(observations.contains("Employee 404 has a reporting line too big. Excess Levels: 1"), "Results not should contain this wrong analysis");
        assertFalse(observations.contains("Employee 403 has a reporting line too big. Excess Levels: 0"), "Results not should contain this wrong analysis");
        
        // Checking salary obs
        String obs = "Employee 700 is earning 1.0 more than allowed.";
        assertTrue(observations.contains(obs), "Results should contain the analysis");
        String obs2 = "Employee 701 is earning 353.20000000000005 less than the minimum.";
        assertTrue(observations.contains(obs2), "Results should contain the analysis");
    }
}
