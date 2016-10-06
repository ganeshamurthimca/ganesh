/**
 * 
 */
package com.davos.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public class User extends DefaultFields {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "userId")
	@JsonProperty("userId")
	private Long userId;
	@Column(name = "userName", unique = true)
	@JsonProperty("userName")
	private String userName;
	@Column(name = "userPassword")
	@JsonProperty("userPassword")
	private String userPassword;
	@Column(name = "accountNonExpired")
	@JsonProperty("accountNonExpired")
	private Boolean accountNonExpired;
	@Column(name = "accountNonLocked")
	@JsonProperty("accountNonLocked")
	private Boolean accountNonLocked;
	@Column(name = "credentialsNonExpired")
	@JsonProperty("credentialsNonExpired")
	private Boolean credentialsNonExpired;

	private Date lastPasswordResetOn;

	public User() {
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
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ " LastPasswordModifiedOn " + lastPasswordResetOn + " ]";
	}
}
