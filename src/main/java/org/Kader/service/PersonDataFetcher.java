package org.Kader.service;

import org.Kader.entities.Person;
import org.Kader.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class PersonDataFetcher implements DataFetcher<Person>{

	
	@Autowired
	private PersonRepository personRepository;
	@Override
	public Person get(DataFetchingEnvironment environment) {
		String email=environment.getArgument("email");
		return personRepository.findOne(email);
	}

}
