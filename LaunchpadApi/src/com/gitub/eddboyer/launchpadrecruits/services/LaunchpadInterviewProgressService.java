package com.gitub.eddboyer.launchpadrecruits.services;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadCandidateProgessBean;
import com.gitub.eddboyer.launchpadrecruits.exceptions.ServiceException;
import com.gitub.eddboyer.launchpadrecruits.util.ToStringReader;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * Service class for handling interviewee progress.
 * 
 * @author Edd Boyer
 */
public class LaunchpadInterviewProgressService extends LaunchpadService {

	/**
	 * Constructor.
	 * 
	 * @param requestBaseString
	 *            the base request url string with trailing slash e.g.
	 *            "http://test.launchpadrecruits.com/1/"
	 * @param username
	 *            the user name to use with the api
	 */
	public LaunchpadInterviewProgressService(String requestBaseString, String username) {
		super(requestBaseString, username);
	}

	/**
	 * Generates a LaunchpadCandidateProgessBean based on the json data in the request body.
	 * 
	 * @param request
	 *            the request
	 * @return a populated {@link LaunchpadCandidateProgessBean}
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	public LaunchpadCandidateProgessBean getCandidateProgess(HttpServletRequest request) throws ServiceException {

		LaunchpadCandidateProgessBean candidateProgessBean;

		Reader reader = null;
		try {
			reader = new ToStringReader(request.getReader());

			candidateProgessBean = getGson().fromJson(reader, LaunchpadCandidateProgessBean.class);

		} catch (JsonSyntaxException e) {
			throw new ServiceException(e);
		} catch (JsonIOException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(e);
		} finally {
			if (reader != null) {
//				try {
//					while (reader.read() > -1) {
//
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Logger.getLogger().info("What i got was this: " + reader.toString());
			}

			IOUtils.closeQuietly(reader);
		}

		return candidateProgessBean;
	}

}
