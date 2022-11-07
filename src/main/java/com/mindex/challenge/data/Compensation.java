package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
*
* @Id to let jpa know which is the unique key.
* String compensationId: it is taken as string because ids can be varchar
*
* Employee employee
*
* double Salary: Salary is taken as double instead of integers just in case if someone has a fractional salary
*
* Date effDate: I have used @JsonFormat which is really helpful to maintain a particular format for the effective date
*
* @Version: Added version to achieve multiple threads. It introduces optimistic locking concept and makes sure updates are only applied to documents with matching version.
* Therefore, the actual value of the version property is added to the update query in a way that the update won’t have any effect if another operation altered the document in between.
* */

@Document(collection = "Compensation")
public class Compensation {
    @Id
    public String compensationId;

    public Employee employee;

    public double salary;

    @JsonFormat(pattern="MM-dd-yyyy")
    private Date effdate;

    @Version
    private Long version;

    public String getCompensationId() {
        return compensationId;
    }

    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getEffdate() {
        return effdate;
    }

    public void setEffdate(Date effdate) {
        this.effdate = effdate;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
