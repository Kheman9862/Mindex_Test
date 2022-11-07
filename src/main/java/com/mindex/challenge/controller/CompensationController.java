package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
*
* This controller consists of 2 endpoints
*
* Get: /compensation/employee/{id}
* Post: /compensation (It takes a RequestBody of compensation modal)
*
*  Also, I am returning a list of compensations in case if any employee gets new type of compensation.
*
* */

@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;


    @GetMapping("/compensation/employee/{id}")
    public List<Compensation> getCompensationByEmployeeId(@PathVariable String id) {
        LOG.debug("Received compensation to read for employeeId is: [{}]", id);

        return compensationService.getCompByEmpId(id);
    }

    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        try {
            LOG.debug("Received compensation to create is: [{}]", compensation);

            return compensationService.createComp(compensation);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }


}
