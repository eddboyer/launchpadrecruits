package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * A Launchpad Recruits interview question.
 * 
 * @author Edd Boyer
 */
public class LaunchpadQuestionBean {

	private static final Integer preferredLimit = Integer.valueOf(60);

	/** Question ID. */
	private Integer questionId;
	/** Interview ID. */
	private Integer interviewId;
	/**
	 * Account ID.
	 * <p>
	 * Currently only used when adding/updating a question.
	 */
	private Integer accountId;
	/** Question text. */
	private String text;
	/**
	 * Time in seconds that a candidate is allowed to answer the question. Allowed values are 30,
	 * 60, 90, and 120.
	 * <p>
	 * Default: 30
	 */
	private Integer limit;
	/**
	 * "success" or "failed". Only used for Delete Question response.
	 */
	private String deleted;

	/**
	 * Constructor.
	 */
	public LaunchpadQuestionBean() {
		// do nothing
	}

	/**
	 * Constructor.
	 * 
	 * @param setDefaults
	 *            if true then defaults will be set
	 */
	public LaunchpadQuestionBean(boolean setDefaults) {
		if (setDefaults) {
			setLimit(preferredLimit);
		}
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets time in seconds that a candidate is allowed to answer the question. Allowed values are
	 * 30, 60, 90, and 120.
	 * <p>
	 * Default: 30
	 * 
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * Sets time in seconds that a candidate is allowed to answer the question. Allowed values are
	 * 30, 60, 90, and 120.
	 * <p>
	 * Default: 30
	 * 
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the interviewId
	 */
	public Integer getInterviewId() {
		return interviewId;
	}

	/**
	 * @param interviewId
	 *            the interviewId to set
	 */
	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	/**
	 * @return the questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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

	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}
