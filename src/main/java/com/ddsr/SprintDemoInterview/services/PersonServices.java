package com.ddsr.SprintDemoInterview.services;

import com.ddsr.SprintDemoInterview.entitys.Person;
import com.ddsr.SprintDemoInterview.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons () {
        return personRepository.findAll();
    }
//    Please extend our existing Person REST API by adding the following endpoints:
//            1. Create a New Person
// Method: POST
//
// Endpoint: /api/persons
// Request Body: Should accept a JSON object with firstName and lastName.
// Action: Add
//}

    public void  addingPerson (Person person) {
        personRepository.save(person);
    }
}
