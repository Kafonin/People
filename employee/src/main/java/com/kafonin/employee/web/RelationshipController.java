package com.kafonin.employee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelationshipController {
    
    @GetMapping("/relationship")
    public String getRelationship() {
        return "relationship";
    }
}