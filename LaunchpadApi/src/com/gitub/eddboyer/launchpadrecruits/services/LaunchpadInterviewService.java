package com.gitub.eddboyer.launchpadrecruits.services;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadCandidateInterviewBean;
import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadInterviewBean;
import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadInviteBean;
import com.gitub.eddboyer.launchpadrecruits.exceptions.ServiceException;
import com.gitub.eddboyer.launchpadrecruits.services.beans.CompletedInterviewListJsonResponse;
import com.gitub.eddboyer.launchpadrecruits.services.beans.InterviewJsonResponse;
import com.gitub.eddboyer.launchpadrecruits.services.beans.InterviewListJsonResponse;
import com.gitub.eddboyer.launchpadrecruits.services.beans.InviteJsonResponse;
import com.google.gson.JsonElement;

/**
 * Service class for managing LaunchPad Recruits interviews.
 * 
 * @author Edd Boyer
 */
public class LaunchpadInterviewService extends LaunchpadService {

	/** Query string for interviews. */
	private static final String requestType = "interviews";

	/**
	 * Constructor.
	 * 
	 * @param requestBaseString
	 *            the base request url string with trailing slash e.g.
	 *            "http://test.launchpadrecruits.com/1/"
	 * @param username
	 *            the user name to use with the api
	 */
	public LaunchpadInterviewService(String requestBaseString, String username) {
		super(requestBaseString, username);
	}

	/**
	 * Gets the list of interviews owned by the specified account from LaunchPad Recruits.
	 * 
	 * @param accountId
	 *            the account id
	 * @return the list of interviews
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public List<LaunchpadInterviewBean> getInterviewList(Integer accountId) throws ServiceException {

		URI uri = URI.create(getRequestString() + "?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri);

		InterviewListJsonResponse response = getGson().fromJson(jsonElement, InterviewListJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets a list of candidates who have completed the specified interview.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the interview id
	 * @return the list of candidates who have done the interview
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public List<LaunchpadCandidateInterviewBean> getCompletedInterviewList(Integer accountId, Integer interviewId)
			throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + interviewId + "/completed?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri);

		CompletedInterviewListJsonResponse response = getGson().fromJson(jsonElement,
				CompletedInterviewListJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Creates a new interview.
	 * 
	 * @param interview
	 *            the interview
	 * @return partially populated version of the bean which includes the id that has been assigned
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadInterviewBean createInterview(LaunchpadInterviewBean interview) throws ServiceException {

		URI uri = URI.create(getRequestString());
		JsonElement jsonElement = getJsonElement(uri, interview, HttpPost.METHOD_NAME);

		InterviewJsonResponse response = getGson().fromJson(jsonElement, InterviewJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets the url for reviewing an interview.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the interview id
	 * @param candidateId
	 *            the candidate id
	 * @return the url
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public String getReviewInterviewLink(Integer accountId, Integer interviewId, String candidateId)
			throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + interviewId + "/review_interview_link");

		Map<String, Object> postData = new HashMap<String, Object>();
		postData.put("candidate_id", candidateId);
		postData.put("account_id", accountId);

		JsonElement jsonElement = getJsonElement(uri, postData, HttpPost.METHOD_NAME);

		InviteJsonResponse response = getGson().fromJson(jsonElement, InviteJsonResponse.class);

		return response.getResponse().getLink().getUrl();

	}

	/**
	 * Updates the interview as defined by the interview id.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interview
	 *            the interview
	 * @return the updated account
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadInterviewBean updateInterview(Integer accountId, LaunchpadInterviewBean interview)
			throws ServiceException {

		interview.setAccountId(accountId);

		URI uri = URI.create(getRequestString() + "/" + interview.getInterviewId());
		JsonElement jsonElement = getJsonElement(uri, interview, HttpPut.METHOD_NAME);

		InterviewJsonResponse response = getGson().fromJson(jsonElement, InterviewJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets the interview with the given id.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the id
	 * @return the interview
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadInterviewBean getInterview(Integer accountId, Integer interviewId) throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + interviewId + "?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri);

		InterviewJsonResponse response = getGson().fromJson(jsonElement, InterviewJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Generates an seamless login interview invite link using a Candidate ID. This is similar to
	 * Invite Link by Candidate, however the main difference is that by using this invite link, the
	 * candidate will not be required to log in using a password.
	 * <p>
	 * This invite method is used to provide a seamless integration with Workdocx and the interview
	 * flow.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the interview id
	 * @param candidateId
	 *            the candidate id
	 * @return the invite
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadInviteBean createInvite(Integer accountId, Integer interviewId, String candidateId)
			throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + interviewId + "/seamless_login_invite");

		Map<String, Object> postData = new HashMap<String, Object>();
		postData.put("candidate_id", candidateId);
		postData.put("account_id", accountId);

		JsonElement jsonElement = getJsonElement(uri, postData, HttpPost.METHOD_NAME);

		InviteJsonResponse response = getGson().fromJson(jsonElement, InviteJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets the public link for the interview with the given id.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the id
	 * @return the public link
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 * @deprecated we won't be using this as we will use the seamless login invite
	 */
	@Deprecated
	public String getPublicLink(Integer accountId, Integer interviewId) throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + interviewId + "/public_link?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri);

		InterviewJsonResponse response = getGson().fromJson(jsonElement, InterviewJsonResponse.class);

		return response.getResponse().getLink();
	}

	/**
	 * @return the request url string (no trailing slash)
	 */
	public String getRequestString() {
		return getRequestBaseString() + requestType;
	}

}
