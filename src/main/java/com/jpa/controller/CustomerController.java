package com.jpa.controller;

import com.controller.SomeException;
import com.jpa.model.Customer;
import com.jpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    CustomerRepository repository;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public Page<Customer> getAllCustomers(@RequestParam(value = "page", required = false) int page,
                                              @RequestParam(value = "size", required = false) int size) throws SomeException {
        return repository.findAll(new PageRequest(page, size));
    }


    @RequestMapping(value = "/customers/customer", method = RequestMethod.GET)
    public List<Customer> findCustomerByLastName(@RequestParam(value = "firstName", required = false) String firstName,
                                                     @RequestParam(value = "lastName", required = false) String lastName)
            throws SomeException {

        if (firstName != null) {
            return repository.getByFirstName(firstName);
        } else if (lastName != null) {
            return repository.readByLastName(lastName);
        }
        return null;
    }

//    @RequestMapping(value = "/customers/customer", method = RequestMethod.GET)
//    public Customer getCustomerById(@RequestParam("id") Long  id) throws SomeException {
//        return repository.find(id);
//    }

    @RequestMapping(value = "/customers/customer", method = RequestMethod.POST)
    public Customer save(@RequestBody Customer customer) {
        Customer save = repository.save(customer);
        return save;
    }

    @RequestMapping(value = "/customers/customer", method = RequestMethod.PUT)
    public Customer update(@RequestBody Customer customer) {
        Customer save = repository.save(customer);
        return save;
    }

    @RequestMapping(value = "/customers/customer", method = RequestMethod.PATCH)
    public Customer update1(@RequestBody Customer customer) {
        Customer save = repository.save(customer);
        return save;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PATCH)
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }


    @RequestMapping(value = "/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id){
       return repository.findOne(id) ;
    }
}