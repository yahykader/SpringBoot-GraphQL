package org.Kader.controller;

import java.util.List;

import org.Kader.entities.Person;
import org.Kader.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@PostMapping("/addPerson")
	public String addPerson(@RequestBody List<Person> persons) {
		personRepository.saveAll(persons);
		return "records inserted "+persons.size();	
	}

	@GetMapping("/findAllPersons")
	public List<Person> findAllPersons(){
		List<Person>persons=personRepository.findAll();
		return persons;
	}
}
