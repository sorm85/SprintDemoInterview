package com.ddsr.SprintDemoInterview.controller;

import com.ddsr.SprintDemoInterview.entitys.Person;
import com.ddsr.SprintDemoInterview.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonServices personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
    @PostMapping(value = "/addingPerson", consumes = "application/json")
    public ResponseEntity<Person> addingPerson (@RequestBody Person person) {
        Person savedPerson = personService.addingPerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }
}
