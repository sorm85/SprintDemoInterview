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
    @GetMapping("/{id}")
    public Person searchPerson (@PathVariable Long id){
        Person person = personService.searchPerson(id);
        return person;
    }

    @PostMapping(value = "/addingPerson", consumes = "application/json")
    public ResponseEntity<Person> addingPerson (@RequestBody Person person) {
        Person savedPerson = personService.addingPerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson (@PathVariable Long id, @RequestBody Person person) {
        Person personLocal = personService.searchPerson(id);

        if (personLocal == null){
            return ResponseEntity.notFound().build();
        }
        personLocal.setFirstName(person.getFirstName());
        personLocal.setLastName(person.getLastName());

        Person updatePerson = personService.addingPerson(personLocal);
        return ResponseEntity.ok(updatePerson);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson (@PathVariable Long id) {
        Person personLocal = personService.searchPerson(id);

        if (personLocal == null){
            return ResponseEntity.notFound().build();
        } else {
            personService.deletePerson(id);

        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("search")
    public ResponseEntity<List<Person>> searchPersonByName (@RequestParam String name) {
        List<Person> persons = personService.searchPersons(name);
        if (persons.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persons);
    }
    @GetMapping("count")
    public ResponseEntity<Long> countPersons () {
        Long count = personService.countPersons();
        if (count == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(count);
    }
}
