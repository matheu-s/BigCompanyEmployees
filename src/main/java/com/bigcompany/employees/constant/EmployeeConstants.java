package com.bigcompany.employees.constant;

/**
 * Utility class to hold constant values related to Employee calculations and rules.
 */
public final class EmployeeConstants {
    private EmployeeConstants(){};
    
    /**
     * The maximum allowed percentage of difference between a manager salary and the average of its subordinates (50%)
     */
    public static final double MAX_SALARY_DIFFERENCE_COEFFICIENT = 1.5;

    /**
     * The minimum allowed percentage of difference between a manager salary and the average of its subordinates (20%)
     */
    public static final double MIN_SALARY_DIFFERENCE_COEFFICIENT = 1.2;

    /**
     * The maximum number of managers allowed between an employee and the CEO
     */
    public static final int MAX_REPORTING_LEVELS_TO_CEO = 4;
    
}
