package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

@Autowired
private PersonRepository personRepository;
    //POST /people - create a new person
    //    Response: 201 Created
    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(personRepository.save(p),HttpStatus.CREATED);
    }

    //GET /people/{id} - Get the person with id number {id}
    //    Response: 200 OK if found, else 404 Not Found
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson (@PathVariable int id){
        //Also needs HttpStatus.BAD_REQUEST if not found?
        return new ResponseEntity<>(personRepository.findOne(id),HttpStatus.OK);
    }

    //GET /people - get the list of all people
    //    Response: 200 OK
    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList(){
        return new ResponseEntity<>(personRepository.findAll(),HttpStatus.OK);
    }

    //PUT /people/{id} - Update the person with id number {id}
    //    Response: 200 OK if updated, 201 Created if a new entity was created
    //*****Should this take an ID?
    @PutMapping("/people{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p){
        if(p.getId() != null) {
            return new ResponseEntity<>(personRepository.save(p), HttpStatus.OK);
        }else{
             return createPerson(p);
        }
    }

    //DELETE /people/{id} - delete the person with id number {id}
    //    Response: 204 No Content
    @DeleteMapping("/people/{id}")
    public ResponseEntity DeletePerson(int id){
        personRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);//Look into how to handle this

    }
}
