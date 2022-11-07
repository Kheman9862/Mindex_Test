package com.mindex.challenge.data;

import org.springframework.data.annotation.Version;

/**
*
* Employee employee
*
* int numberOfReports : Calculating number of reports so integer.
*
* */

public class ReportingStructure {

    public Employee employee;

    public int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Version
    private Long version;

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
