package com.gitub.eddboyer.launchpadrecruits.services;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadCandidateBean;
import com.gitub.eddboyer.launchpadrecruits.exceptions.ServiceException;
import com.gitub.eddboyer.launchpadrecruits.services.beans.CandidateJsonResponse;
import com.google.gson.JsonElement;

/**
 * Service class for managing LaunchPad Recruits candidate accounts.
 * 
 * @author Edd Boyer
 */
public class LaunchpadCandidateService extends LaunchpadService {

	private static final String requestType = "candidates";

	/**
	 * Constructor.
	 * 
	 * @param requestBaseString
	 *            the base request url string with trailing slash e.g.
	 *            "http://test.launchpadrecruits.com/1/"
	 * @param username
	 *            the user name to use with the api
	 */
	public LaunchpadCandidateService(String requestBaseString, String username) {
		super(requestBaseString, username);
	}

	/**
	 * Creates a new candidate.
	 * 
	 * @param candidate
	 *            the candidate
	 * @return fully populated version of the bean which includes the id that has been assigned
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadCandidateBean createCandidate(LaunchpadCandidateBean candidate) throws ServiceException {

		URI uri = URI.create(getRequestString());
		JsonElement jsonElement = getJsonElement(uri, candidate, HttpPost.METHOD_NAME);

		CandidateJsonResponse response = getGson().fromJson(jsonElement, CandidateJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Updates the candidate as defined by the candidate id.
	 * 
	 * @param candidate
	 *            the candidate
	 * @return the updated candidate
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadCandidateBean updateCandidate(LaunchpadCandidateBean candidate) throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + candidate.getCandidateId());
		JsonElement jsonElement = getJsonElement(uri, candidate, HttpPut.METHOD_NAME);

		CandidateJsonResponse response = getGson().fromJson(jsonElement, CandidateJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets the candidate with the given id.
	 * 
	 * @param launchpadCandidateId
	 *            the candidate id
	 * @return the candidate
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadCandidateBean getCandidate(String launchpadCandidateId) throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + launchpadCandidateId);
		JsonElement jsonElement = getJsonElement(uri);

		CandidateJsonResponse response = getGson().fromJson(jsonElement, CandidateJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * @return the request url string (no trailing slash)
	 */
	private String getRequestString() {
		return getRequestBaseString() + requestType;
	}

}
