package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * Candidate interview details.
 * 
 * @author Edd Boyer
 */
public class LaunchpadCandidateInterviewBean extends LaunchpadCandidateBean {

	/** Interview ID. */
	private Integer interviewId;
	/**
	 * Determines the state of the application if an interview is completed. Please refer to
	 * Completed Interview State for details.
	 */
	private LaunchpadCompletedInterviewStateType completedState;

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
	 * @return the completedState
	 */
	public LaunchpadCompletedInterviewStateType getCompletedState() {
		return completedState;
	}

	/**
	 * @param completedState
	 *            the completedState to set
	 */
	public void setCompletedState(LaunchpadCompletedInterviewStateType completedState) {
		this.completedState = completedState;
	}
}
