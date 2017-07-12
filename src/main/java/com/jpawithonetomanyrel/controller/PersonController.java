package com.jpawithonetomanyrel.controller;

import com.controller.SomeException;
import com.jpawithonetomanyrel.model.Party;
import com.jpawithonetomanyrel.model.Person;
import com.jpawithonetomanyrel.repository.PartyRepository;
import com.jpawithonetomanyrel.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);


    @Autowired
    PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Person> getAllPerson() throws SomeException {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        repository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person person) {

        person.getSkills().stream().forEach(skill->skill.setPerson(person));



        return new ResponseEntity<Person>(repository.save(person), HttpStatus.CREATED);
    }

}