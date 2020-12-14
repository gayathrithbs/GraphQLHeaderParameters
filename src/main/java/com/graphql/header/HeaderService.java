package com.graphql.header;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql.context.CustomGraphQLContext;
import com.graphql.entity.Person;
import com.graphql.entity.ResponseEntity;
import com.graphql.exception.InvalidHeaderException;

@Service
public class HeaderService {
	@Autowired
	HeaderRepository headerRepository;
	
	 public HeaderService(final HeaderRepository headerRepository) {
	        this.headerRepository = headerRepository ;
	 }
	static Logger logger = Logger.getLogger(HeaderService.class.getName());
	
	public ResponseEntity createPerson(String firstName, String lastName, CustomGraphQLContext customContext, String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] createPerson in service class starts");
		}
		String headerValue = customContext.getHttpServletRequest().getHeader("headerKey");
		
		if(null != headerValue && headerValue.equals("mykey")) {
			headerRepository.saveAndFlush(new Person(1,firstName,lastName));
			ResponseEntity message = new ResponseEntity();
			message.setMessage("Person has been created successfully!!");
			return message;
		}else {
			logger.error("[Request Id : "+requestId+"] [createPerson] in service : Invalid Header") ;
			throw new InvalidHeaderException("Failure", 500,"Invalid Header");
		}
		
	}

	public List<Person> getPerson(CustomGraphQLContext customContext, String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] getPerson in service class starts");
		}
		String headerValue = customContext.getHttpServletRequest().getHeader("headerKey");
		if(null != headerValue && headerValue.equals("mykey")) {
			return this.headerRepository.findAll().stream().collect(Collectors.toList());
		}else {
			logger.error("[Request Id : "+requestId+"] [getPerson] in service : Invalid Header") ;
			throw new InvalidHeaderException("Failure", 500,"Invalid Header");
		}
	}

}
