# Employees task

Basic Java program that analyzes the company structure

### Overview of the key components
- EmployeeTreeGenerator
  - Located at the service layer, it's responsible for generating the employee tree from the CSV file.
- EmployeeService 
  - Also located at the service layer, serves as the entry point for the app handles analysis once the tree is generated. 


### How to use
- Set an `employee.csv` file under `resources/data` folder or use the sample.
- Run `mvn package` to compile the package.
- Run `java -cp target/employees-1.0-SNAPSHOT.jar src/main/java/com/bigcompany/employees/App.java` to execute the compiled jar.

### Some assumptions:
- The program assumes that all employees are connected (all employees should respond to CEO at some point).
- The program assumes that CSV data is correctly formatted.

### Possible improvements for real apps:
- The program is not fault-tolerant and very strict with file inputs.
- It's reasonable to agree that there are still room for additional unitary tests.
- At the moment, it's a quite simple output with non-formatted high precision doubles.