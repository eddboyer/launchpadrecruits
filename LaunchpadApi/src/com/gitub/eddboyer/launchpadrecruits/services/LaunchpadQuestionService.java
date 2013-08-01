package com.gitub.eddboyer.launchpadrecruits.services;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadQuestionBean;
import com.gitub.eddboyer.launchpadrecruits.exceptions.NotifyUserException;
import com.gitub.eddboyer.launchpadrecruits.exceptions.ServiceException;
import com.gitub.eddboyer.launchpadrecruits.services.beans.QuestionJsonResponse;
import com.gitub.eddboyer.launchpadrecruits.services.beans.QuestionListJsonResponse;
import com.google.gson.JsonElement;

/**
 * Service class for managing LaunchPad Recruits interview questions.
 * 
 * @author Edd Boyer
 */
public class LaunchpadQuestionService extends LaunchpadService {

	/**
	 * Constructor.
	 * 
	 * @param requestBaseString
	 *            the base request url string with trailing slash e.g.
	 *            "http://test.launchpadrecruits.com/1/"
	 * @param username
	 *            the user name to use with the api
	 */
	public LaunchpadQuestionService(String requestBaseString, String username) {
		super(requestBaseString, username);
	}

	/**
	 * Gets the list of questions from the specified interview owned by the specified account from
	 * LaunchPad Recruits.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the interview id
	 * @return the list of questions
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public List<LaunchpadQuestionBean> getQuestionList(Integer accountId, Integer interviewId) throws ServiceException {

		URI uri = URI.create(getRequestString(interviewId) + "?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri);

		QuestionListJsonResponse response = getGson().fromJson(jsonElement, QuestionListJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Creates a new question.
	 * 
	 * @param question
	 *            the question object with the following fields populated: <li>interviewId <li>
	 *            accountId <li>text <li>limit
	 * @return the question
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadQuestionBean addQuestion(LaunchpadQuestionBean question) throws ServiceException {

		URI uri = URI.create(getRequestString(question.getInterviewId()));
		JsonElement jsonElement = getJsonElement(uri, question, HttpPost.METHOD_NAME);

		QuestionJsonResponse response = getGson().fromJson(jsonElement, QuestionJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Updates the question as defined by the question id.
	 * 
	 * @param question
	 *            the question object with the following fields populated: <li>questionId<li>
	 *            interviewId <li>
	 *            accountId <li>text <li>limit
	 * @return the updated question
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadQuestionBean updateQuestion(LaunchpadQuestionBean question) throws ServiceException {

		URI uri = URI.create(getRequestString(question.getInterviewId()) + "/" + question.getQuestionId());
		JsonElement jsonElement = getJsonElement(uri, question, HttpPut.METHOD_NAME);

		QuestionJsonResponse response = getGson().fromJson(jsonElement, QuestionJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets the question with the given id.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the interview id
	 * @param questionId
	 *            the question id
	 * @return the question
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadQuestionBean getQuestion(Integer accountId, Integer interviewId, Integer questionId)
			throws ServiceException {

		URI uri = URI.create(getRequestString(interviewId) + "/" + questionId + "?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri);

		QuestionJsonResponse response = getGson().fromJson(jsonElement, QuestionJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Deletes a question under an interview. Note that an interview must have at least one
	 * question, so you cannot delete a question if it is the last remaining.
	 * 
	 * @param accountId
	 *            the account id
	 * @param interviewId
	 *            the interview id
	 * @param questionId
	 *            the question id
	 * @return true if successful
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 * @throws NotifyUserException
	 *             a {@link NotifyUserException}
	 */
	public boolean deleteQuestion(Integer accountId, Integer interviewId, Integer questionId) throws ServiceException,
			NotifyUserException {

		URI uri = URI.create(getRequestString(interviewId) + "/" + questionId + "?account_id=" + accountId);
		JsonElement jsonElement = getJsonElement(uri, HttpDelete.METHOD_NAME);

		QuestionJsonResponse response = getGson().fromJson(jsonElement, QuestionJsonResponse.class);

		boolean success;
		if (response.getResponse() != null) {
			success = StringUtils.equals("success", response.getResponse().getDeleted());
		} else if (response.getErrorDescription() != null) {
			throw new NotifyUserException(response.getErrorDescription());
		} else {
			success = false;
		}

		return success;
	}

	/**
	 * Gets the request string.
	 * 
	 * @param interviewId
	 *            the interview the question is for/from
	 * @return the request string
	 */
	private String getRequestString(Integer interviewId) {
		return new LaunchpadInterviewService(getRequestBaseString(), getUsername()).getRequestString() + "/"
				+ interviewId + "/questions";
	}
}
