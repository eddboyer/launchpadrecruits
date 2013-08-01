package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * The states possible for completed interviews.
 * 
 * @author Edd Boyer
 */
public enum LaunchpadCompletedInterviewStateType {

	/** Interview is completed and still under review. */
	NEW_APPLICATION("new_application"),
	/** Candidate has been shortlisted for the job. */
	SHORTLISTED("shortlisted"),
	/** Candidate is rejected for the job. */
	REJECTED("rejected");

	private String jsonValue;

	/**
	 * Constructor.
	 * 
	 * @param stateName
	 *            the state name
	 */
	private LaunchpadCompletedInterviewStateType(String stateName) {
		this.jsonValue = stateName;
	}

	/**
	 * Overrides superclass method.
	 * <p>
	 * From overridden method javadoc:
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return jsonValue;
	}

	/**
	 * @return the jsonValue
	 */
	public String getJsonValue() {
		return jsonValue;
	}

	/**
	 * @param jsonValue
	 *            the jsonValue to set
	 */
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
}
