package com.mindex.challenge.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 *
 * @Id to let jpa know which is the unique key.
 * String employeeId: it is taken as string because ids can be varchar
 *
 * String firstname
 * String lastname
 * String position
 * String department
 *
 * List<Employee> directReports: Employees reporting to this employee.
 *
 * @Version: Added version to achieve multiple threads. It introduces optimistic locking concept and makes sure updates are only applied to documents with matching version.
 * Therefore, the actual value of the version property is added to the update query in a way that the update won’t have any effect if another operation altered the document in between.
 * */

@Document(collection = "Employee")
public class Employee {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    @Version
    private Long version;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
