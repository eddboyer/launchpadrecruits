package com.gitub.eddboyer.launchpadrecruits.services;

import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadAccountBean;
import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadAccountDetailsBean;
import com.gitub.eddboyer.launchpadrecruits.exceptions.ServiceException;
import com.gitub.eddboyer.launchpadrecruits.services.beans.AccountJsonResponse;
import com.gitub.eddboyer.launchpadrecruits.services.beans.AccountListJsonResponse;
import com.google.gson.JsonElement;

/**
 * Service class for managing LaunchPad Recruits organisation accounts.
 * 
 * @author Edd Boyer
 */
public class LaunchpadAccountService extends LaunchpadService {

	private static final String requestType = "accounts";

	/**
	 * Constructor.
	 * 
	 * @param requestBaseString
	 *            the base request url string with trailing slash e.g.
	 *            "http://test.launchpadrecruits.com/1/"
	 * @param username
	 *            the user name to use with the api
	 */
	public LaunchpadAccountService(String requestBaseString, String username) {
		super(requestBaseString, username);
	}

	/**
	 * Gets the list of accounts from LaunchPad Recruits.
	 * <p>
	 * Only the following fields are populated in the returned beans:
	 * <li>accountId
	 * <li>companyName
	 * <li>email
	 * 
	 * @return the list of accounts.
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public List<LaunchpadAccountDetailsBean> getAccountList() throws ServiceException {

		URI uri = URI.create(getRequestString());
		JsonElement jsonElement = getJsonElement(uri);

		AccountListJsonResponse response = getGson().fromJson(jsonElement, AccountListJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Creates a new account.
	 * 
	 * @param account
	 *            the account
	 * @return partially populated version of the bean which includes the id that has been assigned
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadAccountBean createAccount(LaunchpadAccountDetailsBean account) throws ServiceException {

		URI uri = URI.create(getRequestString());
		JsonElement jsonElement = getJsonElement(uri, account, HttpPost.METHOD_NAME);

		AccountJsonResponse response = getGson().fromJson(jsonElement, AccountJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Updates the account as defined by the account id.
	 * 
	 * @param account
	 *            the account
	 * @return the updated account
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadAccountDetailsBean updateAccount(LaunchpadAccountDetailsBean account) throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + account.getAccountId());
		JsonElement jsonElement = getJsonElement(uri, account, HttpPut.METHOD_NAME);

		AccountJsonResponse response = getGson().fromJson(jsonElement, AccountJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * Gets the account with the given id.
	 * 
	 * @param launchpadId
	 *            the id
	 * @return the account
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadAccountDetailsBean getAccount(Integer launchpadId) throws ServiceException {

		URI uri = URI.create(getRequestString() + "/" + launchpadId);
		JsonElement jsonElement = getJsonElement(uri);

		AccountJsonResponse response = getGson().fromJson(jsonElement, AccountJsonResponse.class);

		return response.getResponse();
	}

	/**
	 * @return the request url string (no trailing slash)
	 */
	private String getRequestString() {
		return getRequestBaseString() + requestType;
	}

//	public static void main(String[] args) {
//
//		LaunchpadAccountService launchpadAccountService = new LaunchpadAccountService();
//		try {
//			List<LaunchpadAccountDetailsBean> accountList = launchpadAccountService.getAccountList();
//			System.out.println();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
