package dk.serik.recipes.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ExceptionEnvelope {
	private int errorCode;

	private String message;

	private String description;

	private List<ValidationExceptionEnvelope> validationExceptions;

	public ExceptionEnvelope() { }

	public ExceptionEnvelope(int errorCode, String message) {
		this(errorCode, message, null, null);
	}

	public ExceptionEnvelope(int errorCode, String message, String description) {
		this(errorCode, message, description, null);
	}

	public ExceptionEnvelope(int errorCode, String message, String description, List<ValidationExceptionEnvelope> validationExceptions) {
		this.errorCode = errorCode;
		this.message = message;
		this.description = description;
		this.validationExceptions = validationExceptions;
	}

	public void addValidationException(ValidationExceptionEnvelope ve) {
		if (validationExceptions == null) {
			validationExceptions = new ArrayList<>();
		}
		this.validationExceptions.add(ve);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ExceptionEnvelope setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ExceptionEnvelope setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ExceptionEnvelope setDescription(String description) {
		this.description = description;
		return this;
	}

	public List<ValidationExceptionEnvelope> getValidationExceptions() {
		return validationExceptions;
	}

	public ExceptionEnvelope setValidationExceptions(List<ValidationExceptionEnvelope> validationExceptions) {
		this.validationExceptions = validationExceptions;
		return this;
	}

	// For Unit Testing Purpose
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ExceptionEnvelope that = (ExceptionEnvelope) o;
		return errorCode == that.errorCode && Objects.equals(message, that.message) && Objects.equals(description, that.description) && Objects.equals(validationExceptions, that.validationExceptions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(errorCode, message, description, validationExceptions);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "AppExceptionEnvelope [errorCode=" + errorCode + ", message=" + message + ", description=" + description + ", validationExceptions="
					+ validationExceptions + "]";
		}

	}
}
