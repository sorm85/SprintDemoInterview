package com.ddsr.SprintDemoInterview;

import com.ddsr.SprintDemoInterview.controller.PersonController;
import com.ddsr.SprintDemoInterview.entitys.Person;
import com.ddsr.SprintDemoInterview.services.PersonServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonServices personService;

    @Test
    public void testGetAllPersons() {
        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Jane", "Doe");
        when(personService.getAllPersons()).thenReturn(Arrays.asList(person1, person2));

        List<Person> persons = personController.getAllPersons();
        assertEquals(2, persons.size());
    }
}