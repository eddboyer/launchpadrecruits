package com.gitub.eddboyer.launchpadrecruits.beans;

/**
 * An invite for a candidate to an interview.
 * 
 * @author Edd Boyer
 */
public class LaunchpadInviteBean {

	/**
	 * @author Edd Boyer
	 */
	public static class Link {
		/** The invite URL you can send to applicants. */
		private String url;
		/** This parameter can be either "success" or "failed". */
		private String status;
		/** Message supporting the link status. */
		private String message;

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @param url
		 *            the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message
		 *            the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}
	}

	/** Interview ID. */
	private Integer interviewId;
	/** Job title. */
	private String title;
	/** Candidate ID. */
	private String candidateId;
	/** Your specified Candidate ID, usually the same value as the candidate ID used in your system. */
	private String customCandidateId;
	/** Candidate email. */
	private String email;
	/**
	 * This parameter consists of the following sub-parameters: <li>url – The invite URL you can
	 * send to applicants. <li>status – This parameter can be either "success" or "failed". <li>
	 * message – Message supporting the link status
	 */
	private Link link;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

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
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(Link link) {
		this.link = link;
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

}
