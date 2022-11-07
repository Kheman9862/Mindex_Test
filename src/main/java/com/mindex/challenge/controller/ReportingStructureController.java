package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
* Added a new endpoint in this controller /reportingStructure/{employeeId}
* This controller consist of just one read endpoint.
*
* */

@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/reportingStructure/{employeeId}")
    public ReportingStructure read(@PathVariable String employeeId) {
       try{

        LOG.debug("Reporting Structure with the given employeeId in controller is: [{}]", employeeId);

        return employeeService.GetReportingStructure(employeeId);
       }
       catch (Exception e){
           throw new RuntimeException(e.getMessage());
        }
    }

}
