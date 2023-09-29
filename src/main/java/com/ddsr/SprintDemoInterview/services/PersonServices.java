package com.ddsr.SprintDemoInterview.services;

import com.ddsr.SprintDemoInterview.entitys.Person;
import com.ddsr.SprintDemoInterview.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonServices {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons () {
        return personRepository.findAll();
    }
}
