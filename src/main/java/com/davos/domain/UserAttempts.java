/**
 * 
 */
package com.davos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@Entity
@Table(name = "user_attempts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAttempts implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "userAttemptsId")
	@JsonProperty("userAttemptsId")
	private Long userAttemptsId;

	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@OneToOne
	@JsonProperty("userId")
	private User userId;

	@Column(name = "attempts")
	@JsonProperty("attempts")
	private int attempts;

	@Column(name = "lastModified")
	@JsonProperty("lastModified")
	private Date lastModified;

	public Long getUserAttemptsId() {
		return userAttemptsId;
	}

	public void setUserAttemptsId(Long userAttemptsId) {
		this.userAttemptsId = userAttemptsId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
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
