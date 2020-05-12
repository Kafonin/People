package com.kafonin.employee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PeopleController {
    
    /*@Autowired
    private PeopleRepository peopleRepository;*/
    
    @RequestMapping("/people")
    public String getPeople(){
        return "people";
    }
}

/*    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        People people = new People();
        model.addAttribute("people", people);
    
        return "people";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
        public String saveProduct(@ModelAttribute("product") People people) {
            peopleRepository.save(people);
	
	    return "people";
    }*/