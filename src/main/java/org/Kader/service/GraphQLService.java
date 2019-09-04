package org.Kader.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

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
	
	
	
	   @PostConstruct
	    private void loadSchema() throws IOException {

	        //Load Books into the Book Repository
	       // loadDataIntoHSQL();

	        // get the schema
	        File schemaFile = resource.getFile();
	        // parse schema
	        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
	        RuntimeWiring wiring = buildRuntimeWiring();
	        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
	        graphQL = GraphQL.newGraphQL(schema).build();
	    }


		private RuntimeWiring buildRuntimeWiring() {
			// TODO Auto-generated method stub
			return null;
		}
	
	

}
