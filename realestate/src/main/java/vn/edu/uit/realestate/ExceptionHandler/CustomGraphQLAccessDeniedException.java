package vn.edu.uit.realestate.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class CustomGraphQLAccessDeniedException extends RuntimeException implements GraphQLError {
	private static final long serialVersionUID = 1L;
	private final int errorCode;
	
	public CustomGraphQLAccessDeniedException() {
		super("AccessDeniedException: You're not allowed to access this method");
        this.errorCode = 401;
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
