package com.ddsr.SprintDemoInterview.repos;

import com.ddsr.SprintDemoInterview.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Long> {
}
