package com.jpawithonetomanyrel.repository;

import com.jpawithonetomanyrel.model.Party;
import com.jpawithonetomanyrel.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}