package com.graphql.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InvalidHeaderException extends RuntimeException implements GraphQLError {

	private static final long serialVersionUID = 1L;
	private String status;
	private int statusCode;
	private String message;
	
	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, Object> getExtensions() {
		Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("statusCode", this.getStatusCode());
        customAttributes.put("status", this.getStatus());
        customAttributes.put("message", this.getMessage());
        return customAttributes;
	}

}
