package com.gitub.eddboyer.launchpadrecruits.services.beans;

import java.util.List;
import java.util.Map;

/**
 * Response from LaunchPad Recruits.
 * <p>
 * Error response example:
 * {"error":"invalid_resource","error_description":"The current resource was deemed invalid."
 * ,"messages"
 * :{"first_name":["Please enter your first name."],"last_name":["Please enter your last name."
 * ],"email":["Email is required."]}}
 * 
 * @author Edd Boyer
 * 
 * @param <T>
 *            the intended response object
 */
public class LaunchpadJsonResponse<T> {

	private T response;
	private String error;
	private String errorDescription;
	private Map<String, List<String>> messages;

	/**
	 * @return the response
	 */
	public T getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(T response) {
		this.response = response;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription
	 *            the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * @return the messages
	 */
	public Map<String, List<String>> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(Map<String, List<String>> messages) {
		this.messages = messages;
	}
}
