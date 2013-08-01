package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * 
 * 
 * @author Edd Boyer
 */
public class LaunchpadCandidateProgessBean {

	/**
	 * @author Edd Boyer
	 */
	public static class Review {

		/** The review interview URL. */
		private String link;
		/**
		 * When using the link in an iframe, this value reflects the approximate height needed to
		 * display the contents without overflowing (i.e. scrollbars are showing).
		 */
		private Integer minHeight;
		/**
		 * When using the link in an iframe, this value reflects the approximate width needed to
		 * display the contents without overflowing (i.e. scrollbars are showing).
		 */
		private Integer minWidth;

		/**
		 * @return the link
		 */
		public String getLink() {
			return link;
		}

		/**
		 * @param link
		 *            the link to set
		 */
		public void setLink(String link) {
			this.link = link;
		}

		/**
		 * @return the minHeight
		 */
		public Integer getMinHeight() {
			return minHeight;
		}

		/**
		 * @param minHeight
		 *            the minHeight to set
		 */
		public void setMinHeight(Integer minHeight) {
			this.minHeight = minHeight;
		}

		/**
		 * @return the minWidth
		 */
		public Integer getMinWidth() {
			return minWidth;
		}

		/**
		 * @param minWidth
		 *            the minWidth to set
		 */
		public void setMinWidth(Integer minWidth) {
			this.minWidth = minWidth;
		}

	}

	/** Candidate ID. */
	private String candidateId;
	/**
	 * Your specified Candidate ID, usually the same value as the candidate ID used in your system.
	 */
	private String customCandidateId;
	/** Interview ID. */
	private Integer interviewId;
	/** Please refer to the Application Statuses table for the complete list. */
	private LaunchpadApplicationStatusType status;
	/**
	 * If the status is 'question', this is the question number that the candidate is currently
	 * recording.
	 */
	private String questionNumber;
	/**
	 * If the status is 'finished', this contains the details of the review interview link. You can
	 * then use this URL in a frame or as a standalone page to display the video interview.
	 */
	private Review review;

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
	 * @return the customCandidateId
	 */
	public String getCustomCandidateId() {
		return customCandidateId;
	}

	/**
	 * @param customCandidateId
	 *            the customCandidateId to set
	 */
	public void setCustomCandidateId(String customCandidateId) {
		this.customCandidateId = customCandidateId;
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
	 * @return the status
	 */
	public LaunchpadApplicationStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(LaunchpadApplicationStatusType status) {
		this.status = status;
	}

	/**
	 * @return the questionNumber
	 */
	public String getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * @param questionNumber
	 *            the questionNumber to set
	 */
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * @return the review
	 */
	public Review getReview() {
		return review;
	}

	/**
	 * @param review
	 *            the review to set
	 */
	public void setReview(Review review) {
		this.review = review;
	}
}
