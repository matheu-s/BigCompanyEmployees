package com.bigcompany.employees.model;

/**
 * Represents an employee in the company.
 * Each employee has a unique ID, a name, a salary, and an optional manager ID.
 */
public class Employee {
    
    /**
     * The unique identifier for the employee.
     */
    private final int id;

    /**
     * The first name of the employee.
     */
    private String firstName;

    /**
     * The last name of the employee.
     */
    private String lastName;

    /**
     * The salary of the employee.
     */
    private double salary;

    /**
     * The unique identifier of the employee's manager. Can be null for CEO.
     */
    private Integer managerId;

    /**
     * Constructs an Employee with the specified details.
     *
     * @param id        The unique identifier for the employee.
     * @param firstName  The first name of the employee.
     * @param lastName  The first name of the employee.
     * @param salary    The salary of the employee.
     * @param managerId The unique identifier of the employee's manager. Can be null for CEO.
     */
    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
