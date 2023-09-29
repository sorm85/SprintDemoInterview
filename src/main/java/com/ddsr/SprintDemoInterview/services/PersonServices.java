package com.ddsr.SprintDemoInterview.services;

import com.ddsr.SprintDemoInterview.entitys.Person;
import com.ddsr.SprintDemoInterview.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PersonServices {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons () {
        return personRepository.findAll() ;
    }
//    Please extend our existing Person REST API by adding the following endpoints:
//            1. Create a New Person
// Method: POST
//
// Endpoint: /api/persons
// Request Body: Should accept a JSON object with firstName and lastName.
// Action: Add
//}

    public Person  addingPerson (Person person) {
        return personRepository.save(person);
    }

    public Person searchPerson (Long id) {
        return personRepository.findById(id).orElse(null);
    }
    public void deletePerson (Long id) {
        Person person = personRepository.getById(id);
         personRepository.delete(person);
    }
    public List<Person> searchPersons (String str) {
        return personRepository.findByFirstNameContainingOrLastNameContaining(str, str);
    }

    public Long countPersons (){
        return personRepository.count();
    }


}
