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
}
