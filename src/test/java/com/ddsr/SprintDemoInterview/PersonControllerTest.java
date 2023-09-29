package com.ddsr.SprintDemoInterview;

import com.ddsr.SprintDemoInterview.controller.PersonController;
import com.ddsr.SprintDemoInterview.entitys.Person;
import com.ddsr.SprintDemoInterview.services.PersonServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PersonServices personService;

    @Test
    public void testGetAllPersons() throws Exception {
        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Jane", "Doe");
        Mockito.when(personService.getAllPersons()).thenReturn(Arrays.asList(person1, person2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("Jane")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("Doe")));
    }


    @Test
    public void testCreatePerson() throws Exception {
        Person person = new Person("John", "Doe");
        person.setId(1L);

        Mockito.when(personService.addingPerson(Mockito.any(Person.class))).thenReturn(person);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/persons/addingPerson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }
    @Test
    public void testSearchPerson() throws Exception {
        Person person = new Person("John", "Doe");
        person.setId(1L);

        Mockito.when(personService.searchPerson(Mockito.any(Long.class))).thenReturn(person);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }
    @Test
    public void testUpdatePerson() throws Exception {
        Long personId = 1L;
        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Jane", "Doe");

        person1.setId(personId);
        person2.setId(personId);


        Mockito.when(personService.searchPerson(Mockito.any(Long.class))).thenReturn(person1);
        Mockito.when(personService.addingPerson(Mockito.any(Person.class))).thenReturn(person2);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/persons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(person1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));

//        Mockito.verify(personService).searchPerson(personId);
//        Mockito.verify(personService).addingPerson(person2);

    }
}
