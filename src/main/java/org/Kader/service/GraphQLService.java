package org.Kader.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.Kader.entities.Person;
import org.Kader.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GraphQLService {
	
		@Value("classpath:person.graphql")
		public Resource resource;
		
		private GraphQL graphQL;
		
		@Autowired
		private AllPersonDataFetcher getAllPersonsDataFetcher;
		@Autowired
		private PersonDataFetcher findPersonDataFetcher;
		@Autowired
		private PersonRepository personRepository;
	
	
	
	   @PostConstruct
	    private void loadSchema() throws IOException {

	        //Load Books into the Book Repository
	        loadDataIntoHSQL();

	        // get the schema
	        File schemaFile = resource.getFile();
	        // parse schema
	        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
	        RuntimeWiring wiring = buildRuntimeWiring();
	        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
	        graphQL = GraphQL.newGraphQL(schema).build();
	    }


		private void loadDataIntoHSQL() {
		    Stream.of(new Person("123","kader","0745857845","yah@gmail.com",new String[] {"100 rue de la chapelle","75018","Paris-France"}),
		    		 new Person("124","mounir","0745857848","yah@gmail.com",new String[] {"99 rue de la chapelle","75018","Paris-France"}),
		    		 new Person("125","houcin","0745857846","yah@gmail.com",new String[] {"98 rue de la chapelle","75018","Paris-France"}))
		          .forEach(person->{
		    	         personRepository.save(person);
		    });
	      }


		private RuntimeWiring buildRuntimeWiring() {
			return RuntimeWiring.newRuntimeWiring()
					            .type("Query", typeWiring->typeWiring
					                          .dataFetcher("getAllPersons", getAllPersonsDataFetcher)
					                          .dataFetcher("findPerson", findPersonDataFetcher))           
					            .build();
		}
		
		public GraphQL graphQL() {
			return graphQL;
		}
	
	

}
