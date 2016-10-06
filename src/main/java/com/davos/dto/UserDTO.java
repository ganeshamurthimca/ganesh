/**
 * 
 */
package com.davos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends DefaultFields {

	private static final long serialVersionUID = 1L;
	@JsonProperty("userId")
	private Long userId;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("userPassword")
	private String userPassword;
	@JsonProperty("accountNonExpired")
	private Boolean accountNonExpired = Boolean.TRUE;
	@JsonProperty("accountNonLocked")
	private Boolean accountNonLocked = Boolean.TRUE;
	@JsonProperty("credentialsNonExpired")
	private Boolean credentialsNonExpired = Boolean.TRUE;

	private Date lastPasswordResetOn;

	/**
	 * 
	 */
	public UserDTO() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Date getLastPasswordResetOn() {
		return lastPasswordResetOn;
	}

	public void setLastPasswordResetOn(Date lastPasswordResetOn) {
		this.lastPasswordResetOn = lastPasswordResetOn;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ " LastPasswordModifiedOn " + lastPasswordResetOn + " ]";
	}

}
