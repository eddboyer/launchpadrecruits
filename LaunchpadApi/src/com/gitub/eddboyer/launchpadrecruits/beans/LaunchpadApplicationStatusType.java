package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * Application statuses provided by the Callback API.
 * 
 * @author Edd Boyer
 */
public enum LaunchpadApplicationStatusType {

	/** The candidate saw the filter question(s). */
	VIEW_FILTER_QUESTIONS("view_filter_questions"),
	/** The candidate answered no to at least one filter question. */
	FILTERED_OUT("filtered_out"),
	/** The candidate answered yes to all filter questions. */
	FILTERED_IN("filtered_in"),
	/** The candidate saw the custom introduction videos. */
	VIEW_BRANDED_VIDEO("view_branded_video"),
	/** The candidate is setting up his camera and microphone. */
	SETUP_PROCESS("setup_process"),
	/** The candidate saw the practice question. */
	VIEW_PRACTICE_QUESTION("view_practice_question"),
	/**
	 * The candidate saw one of the interview questions, or is currently recording an interview
	 * question. This is accompanied by the question_number parameter to determine the current
	 * question.
	 */
	QUESTION("question"),
	/**
	 * The candidate answered/recorded all questions, but not yet provided additional details (if
	 * requested).
	 */
	FINAL("final"),
	/**
	 * The candidate answered/recorded all questions and provided additional details (if requested).
	 * If the status_frequency parameter is set to 'final', then this will be the only status sent
	 * to the callback URL.
	 */
	FINISHED("finished");

	private String jsonValue;

	/**
	 * Constructor.
	 * 
	 * @param stateName
	 *            the state name
	 */
	private LaunchpadApplicationStatusType(String stateName) {
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
