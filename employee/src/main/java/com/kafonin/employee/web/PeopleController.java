package com.kafonin.employee.web;

import com.kafonin.employee.model.People;
import com.kafonin.employee.repository.PeopleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeopleController {
    
    @Autowired
    private PeopleRepository peopleRepository;
    
    @GetMapping("/people")
    public String getPeople(){
        return "people";
    }

    // Begin Dynamo DB C.R.U.D.
    @PostMapping(path = "/savePeople")
	public String insertIntoDynamoDB(People people) {
		peopleRepository.insertIntoDynamoDB(people);
		return "people";
	}

	@GetMapping
	public ResponseEntity<People> getOnePeopleDetails(@RequestParam String peopleId, @RequestParam String lastName) {
		People people = peopleRepository.getOnePeopleDetails(peopleId, lastName);
		return new ResponseEntity<People>(people, HttpStatus.OK);
	}

	@PutMapping
	public void updatePeopleDetails(@RequestBody People people) {
		peopleRepository.updatePeopleDetails(people);
	}

	@DeleteMapping(value = "{peopleId}/{lastName}")
	public void deletePeopleDetails(@PathVariable("peopleId") String peopleId,
			@PathVariable("lastName") String lastName) {
        People people = new People();
		people.setPeopleId(peopleId);
		people.setLastName(lastName);
		peopleRepository.deletePeopleDetails(people);
	}
}

/*    @GetMapping("/savePeople")
    public String addPeople(@ModelAttribute("people") People people, Model model) {
        peopleRepository.save(people);

        List<People> peoples = (List<People>) peopleRepository.findAll();
        model.addAttribute("peoples", peoples);
        return "people";
    }*/