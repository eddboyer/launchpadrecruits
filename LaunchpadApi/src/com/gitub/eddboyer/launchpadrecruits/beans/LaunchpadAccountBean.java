package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * A Launchpad Recruits account.
 * 
 * @author Edd Boyer
 */
public class LaunchpadAccountBean {

	/** Unique account ID. */
	private Integer accountId;
	/**
	 * The account company name.
	 * <p>
	 * Required yes
	 */
	private String companyName;
	/**
	 * The account main email address.
	 * <p>
	 * Required yes
	 */
	private String email;

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
