package com.jpawithonetomanyrel.controller;

import com.controller.SomeException;
import com.jpawithonetomanyrel.model.Book;
import com.jpawithonetomanyrel.model.Party;
import com.jpawithonetomanyrel.repository.BookRepository;
import com.jpawithonetomanyrel.repository.PartyRepository;
import com.jpawithonetomanyrel.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parties")
public class PartyController {
    private static final Logger logger = LoggerFactory.getLogger(PartyController.class);


    @Autowired
    PartyRepository repository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Party> getAllBooks() throws SomeException {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePartyn(@PathVariable long id) {
        repository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addParty(@RequestBody Party party) {

        party.getPeople().stream().forEach(people-> people.getSkills().forEach(skill -> skill.setPerson(people)));
        return new ResponseEntity<>(repository.save(party), HttpStatus.CREATED);
    }

}