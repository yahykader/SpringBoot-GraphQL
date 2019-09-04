package org.Kader.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.Kader.entities.Person;
import org.Kader.repository.PersonRepository;
import org.Kader.service.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
@RequestMapping("/rest/person")
public class PersonController {
	
	//@Autowired
    //GraphQLService graphQLService;
	
	@Value("classpath:person.graphql")
	public Resource resource;
	
	private GraphQL graphQL;
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
		@SuppressWarnings("unused")
		DataFetcher<List<Person>>fetcher1=data->{
			          return (List<Person>)personRepository.findAll();
			          };
		@SuppressWarnings("unused")
		DataFetcher<Person>fetcher2=data->{
				          return personRepository.findByEmail(data.getArgument("email"));
				          };		          
	
		return RuntimeWiring.newRuntimeWiring()
				            .type("Query", typeWiring->typeWiring
				                          .dataFetcher("getAllPersons", fetcher1)
				                          .dataFetcher("findPerson", fetcher2))           
				            .build();
	}
	
	public GraphQL graphQL() {
		return graphQL;
	}

	
	
	
	
	@PostMapping
	public ResponseEntity<Object> getAllPersons(@RequestBody String query) {
		ExecutionResult result=graphQL.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	
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
