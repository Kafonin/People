package com.kafonin.employee.repository;

import com.kafonin.employee.model.People;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends CrudRepository<People, Long>{
    
}