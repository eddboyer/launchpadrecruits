package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * Bean representing a candidate for a launchpad recruits interview.
 * 
 * @author Edd Boyer
 */
public class LaunchpadCandidateBean {

	/** Candidate ID. e.g. cnd_98ee9e0daef8b0bc58a3d8ccb75cd377 */
	private String candidateId;
	/** Candidate email. */
	private String email;
	/** Candidate first name. */
	private String firstName;
	/** Candidate last name. */
	private String lastName;

	/**
	 * @return the candidateId
	 */
	public String getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId
	 *            the candidateId to set
	 */
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
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
}
