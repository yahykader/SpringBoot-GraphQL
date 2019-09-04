package org.Kader.controller;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.Kader.entities.Person;
import org.Kader.repository.PersonRepository;
import org.Kader.service.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/rest/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private GraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllPersons(@RequestBody String query) {
		ExecutionResult excute=graphQLService.graphQL().execute(query);
		return new ResponseEntity<Object>(excute,HttpStatus.OK);
	}
	
	/*@PostMapping("/addPerson")
	public String addPerson(@RequestBody List<Person> persons) {
		personRepository.saveAll(persons);
		return "records inserted "+persons.size();	
	}

	@GetMapping("/findAllPersons")
	public List<Person> findAllPersons(){
		List<Person>persons=personRepository.findAll();
		return persons;
	}*/
}
