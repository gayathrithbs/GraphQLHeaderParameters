package com.graphql.header;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.context.CustomGraphQLContext;
import com.graphql.entity.Person;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class HeaderQuery implements GraphQLQueryResolver {

	static Logger logger = Logger.getLogger(HeaderMutation.class.getName());
	@Autowired
	private HeaderService headerService;
	
	public List<Person> getPerson(DataFetchingEnvironment environment){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  getPerson in HeaderQuery starts");
		}
		CustomGraphQLContext customContext = environment.getContext();
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getPerson in HeaderQuery ends");
		}
		List<Person> person = headerService.getPerson(customContext, requestId);
		return person;
		
	}
	
}
