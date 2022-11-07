package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* Created a new Compensation repo to persist and query the Compensation from the persistence layer
* */

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, Employee> {
    List<Compensation> findByEmployee(Employee employee);
}
