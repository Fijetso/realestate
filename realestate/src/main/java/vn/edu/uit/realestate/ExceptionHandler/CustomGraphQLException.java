package vn.edu.uit.realestate.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class CustomGraphQLException extends RuntimeException implements GraphQLError {
	private final int errorCode;
	
	public CustomGraphQLException(int errorCode, String message) {
		super(message);
        this.errorCode = errorCode;
    }

	@Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();

        customAttributes.put("statusCode", this.errorCode);
        customAttributes.put("message", this.getMessage());

        return customAttributes;
    }
	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return ErrorType.DataFetchingException;
	}

}
