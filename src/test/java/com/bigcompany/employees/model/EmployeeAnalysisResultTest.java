package com.bigcompany.employees.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeAnalysisResultTest {
    EmployeeAnalysisResult result;
    
    void setUpCeoCaseCorrect() {
        // Setting up a ceo analysis with allowed thresholds
        double minSalary = 40000;
        double employeeSalary = 50000;
        double maxSalary = 65000;
        
        Employee employeeCeo = new Employee(0, "matheus", "souza", employeeSalary, null);

        result = new EmployeeAnalysisResult(
                employeeCeo, 
                0,
                4,
                minSalary,
                maxSalary,
                true
        );
    }
    
    void setUpCeoCaseHighSalary() {
        // Setting up a ceo analysis with allowed thresholds
        double minSalary = 40000;
        double employeeSalary = 75000;
        double maxSalary = 70000;

        Employee employeeCeo = new Employee(0, "matheus", "souza", employeeSalary, null);

        result = new EmployeeAnalysisResult(
                employeeCeo,
                0,
                4,
                minSalary,
                maxSalary,
                true
        );
    }
    
    @Test
    void testAnalysisForCeo() {
        // No observations are made for correct employee
        setUpCeoCaseCorrect();
        assertTrue(result.getObservations().isEmpty());

        // Salary is too high
        setUpCeoCaseHighSalary();
        String obs = "Employee 0 is earning " + 5000.0 + " more than allowed.";
        assertEquals(obs, result.getObservations().getFirst());
    }
    
    void setUpManagerRightCase() {
        // Setting up a manager with allowed thresholds
        double minSalary = 40000;
        double employeeSalary = 50000;
        double maxSalary = 65000;

        Employee employeeCeo = new Employee(5, "matheus", "souza", employeeSalary, 0);

        result = new EmployeeAnalysisResult(
                employeeCeo,
                2,
                4,
                minSalary,
                maxSalary,
                true
        );
    }

    void setUpManagerLowSalaryCase() {
        // Setting up a manager with allowed thresholds
        double minSalary = 40000;
        double employeeSalary = 30000;
        double maxSalary = 65000;

        Employee employee = new Employee(5, "matheus", "souza", employeeSalary, 0);

        result = new EmployeeAnalysisResult(
                employee,
                2,
                4,
                minSalary,
                maxSalary,
                true
        );
    }

     void setUpManagerHighSalaryCase() {
        // Setting up a manager with allowed thresholds
        double minSalary = 40000;
        double employeeSalary = 100000.500;
        double maxSalary = 65000;

        Employee employee = new Employee(5, "matheus", "souza", employeeSalary, 0);

        result = new EmployeeAnalysisResult(
                employee,
                2,
                4,
                minSalary,
                maxSalary,
                true
        );
    }
    
    @Test
    public void testAnalysisManager() {
        // No observations are made for correct employee
        setUpManagerRightCase();
        assertTrue(result.getObservations().isEmpty());

        // One obs for low salary
        setUpManagerLowSalaryCase();
        String obs = "Employee 5 is earning " + 10000.0 + " less than the minimum.";
        assertEquals(obs, result.getObservations().getFirst());

        // One obs for high salary
        setUpManagerHighSalaryCase();
        String obs2 = "Employee 5 is earning " + 35000.5 + " more than allowed.";
        assertEquals(obs2, result.getObservations().getFirst());
    }
    
    void setUpNoManagerBigReportLineCase() {
        // Setting up an employee that is not a manager but has too many levels
        double minSalary = 0;
        double employeeSalary = 100000.500;
        double maxSalary = 0;

        Employee employee = new Employee(50, "matheus", "souza", employeeSalary, 10);

        result = new EmployeeAnalysisResult(
                employee,
                6,
                4,
                minSalary,
                maxSalary,
                false
        );
    }
    
    @Test
    void testAnalysisNoManager() {
        // One observation is done
        setUpNoManagerBigReportLineCase();
        assertFalse(result.getObservations().isEmpty());
        
        String obs = "Employee 50 has a reporting line too big. Excess Levels: 1";
        assertEquals(obs, result.getObservations().getFirst());
    }
    
}
