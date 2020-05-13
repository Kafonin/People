package com.kafonin.employee.service;

import javax.transaction.Transactional;

import com.kafonin.employee.model.People;
import com.kafonin.employee.repository.PeopleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepo;
    
    public Iterable<People> listAll() {
		return peopleRepo.findAll();
	}
	
	public void save(People people) {
		peopleRepo.save(people);
	}
	
	public People get(long id) {
		return peopleRepo.findById(id).get();
	}
	
	public void delete(long id) {
		peopleRepo.deleteById(id);
	}
}