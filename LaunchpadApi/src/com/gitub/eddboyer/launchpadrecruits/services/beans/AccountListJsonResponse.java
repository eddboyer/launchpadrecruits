package com.gitub.eddboyer.launchpadrecruits.services.beans;

import java.util.List;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadAccountDetailsBean;

/**
 * Account list json response.
 * 
 * @author Edd Boyer
 */
public class AccountListJsonResponse extends LaunchpadJsonResponse<List<LaunchpadAccountDetailsBean>> {

	private Integer count;

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}
