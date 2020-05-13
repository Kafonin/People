package com.kafonin.employee.web;

import java.util.List;

import com.kafonin.employee.model.People;
import com.kafonin.employee.repository.PeopleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PeopleController {
    
    @Autowired
    private PeopleRepository peopleRepository;
    
    @RequestMapping("/people")
    public String getPeople(){
        return "people";
    }

    @GetMapping("/savePeople")
    public String addPeople(@ModelAttribute("people") People people, Model model) {
        peopleRepository.save(people);

        List<People> peoples = (List<People>) peopleRepository.findAll();
        model.addAttribute("peoples", peoples);
        return "people";
    }
}