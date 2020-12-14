package com.graphql.header;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.context.CustomGraphQLContext;
import com.graphql.entity.ResponseEntity;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class HeaderMutation implements GraphQLMutationResolver{
	static Logger logger = Logger.getLogger(HeaderMutation.class.getName());
	@Autowired
	private HeaderService headerService;
	
	public ResponseEntity createPerson(String firstName, String lastName, DataFetchingEnvironment environment){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  createPerson in HeaderMutation starts");
		}
		CustomGraphQLContext customContext = environment.getContext();
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] createPerson in HeaderMutation ends");
		}
		ResponseEntity entity = headerService.createPerson(firstName,lastName, customContext, requestId);
		return entity;
		
	}
	
	

}
