/**
 * 
 */
package com.davos.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
public class UserAttemptsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userAttemptsId;

	@JsonProperty("userId")
	private UserDTO userId;

	@JsonProperty("attempts")
	private int attempts;

	@JsonProperty("lastModified")
	private Date lastModified;

	public Long getUserAttemptsId() {
		return userAttemptsId;
	}

	public void setUserAttemptsId(Long userAttemptsId) {
		this.userAttemptsId = userAttemptsId;
	}

	public UserDTO getUserId() {
		return userId;
	}

	public void setUserId(UserDTO userId) {
		this.userId = userId;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
