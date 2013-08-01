package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * A Launchpad Recruits account with a bunch o' extra details.
 * 
 * @author Edd Boyer
 */
public class LaunchpadAccountDetailsBean extends LaunchpadAccountBean {

	/**
	 * First name of the main account user.
	 * <p>
	 * Required yes
	 */
	private String firstName;
	/**
	 * Last name of the main account user.
	 * <p>
	 * Required yes
	 */
	private String lastName;
	/**
	 * If true, the email specified in the 'email' parameter will receive notifications regarding
	 * the interview process. If set to false, these emails are suppressed.
	 * <p>
	 * Default false<br>
	 * Required no
	 */
	private boolean emailEmployers;
	/**
	 * If true, the candidates will receive notifications regarding the interview process. If set to
	 * false, these emails are suppressed.
	 * <p>
	 * Default true<br>
	 * Required no
	 */
	private boolean emailApplicants;
	/**
	 * URL of the company logo to be used in the video interview process. The image file will be
	 * uploaded programatically to LaunchpadRecruits and will be loaded from our application.
	 * <p>
	 * Required yes
	 */
	private String logoUrl;
	/**
	 * Specifies the URL where status notifications will be sent from LaunchPad Recruits.
	 * <p>
	 * Required yes
	 */
	private String callbackUrl;
	/**
	 * Sets the frequency of the status notifications sent to the callback_url. If set to 'all', all
	 * status updates are sent as the interview progresses. If set to 'final', status updates are
	 * sent only when the interview is completed.
	 * <p>
	 * Required no Default all
	 */
	private String statusFrequency;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailEmployers
	 */
	public boolean isEmailEmployers() {
		return emailEmployers;
	}

	/**
	 * @param emailEmployers
	 *            the emailEmployers to set
	 */
	public void setEmailEmployers(boolean emailEmployers) {
		this.emailEmployers = emailEmployers;
	}

	/**
	 * @return the emailApplicants
	 */
	public boolean isEmailApplicants() {
		return emailApplicants;
	}

	/**
	 * @param emailApplicants
	 *            the emailApplicants to set
	 */
	public void setEmailApplicants(boolean emailApplicants) {
		this.emailApplicants = emailApplicants;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl
	 *            the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the callbackUrl
	 */
	public String getCallbackUrl() {
		return callbackUrl;
	}

	/**
	 * @param callbackUrl
	 *            the callbackUrl to set
	 */
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	/**
	 * @return the statusFrequency
	 */
	public String getStatusFrequency() {
		return statusFrequency;
	}

	/**
	 * @param statusFrequency
	 *            the statusFrequency to set
	 */
	public void setStatusFrequency(String statusFrequency) {
		this.statusFrequency = statusFrequency;
	}

}
