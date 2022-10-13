package com.evaluation.client.repository;


import com.evaluation.client.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByIdentify(String identification);

    Optional<Person> findById(Integer id);

    Person save(Person person);

    Person deleteByIdentify(String Identify);

}
