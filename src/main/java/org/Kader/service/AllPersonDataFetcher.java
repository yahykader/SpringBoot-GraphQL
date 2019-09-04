/*package org.Kader.service;

import java.util.List;

import org.Kader.entities.Person;
import org.Kader.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
@Component
public class AllPersonDataFetcher implements DataFetcher<List<Person>>{
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> get(DataFetchingEnvironment environment) {
		return personRepository.findAll();
	}

}
*/