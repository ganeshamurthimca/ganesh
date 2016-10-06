/**
 * 
 */
package com.davos.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@Entity
@Table(name = "user_role_mapping", indexes = { @Index(columnList = "userId,roleId", unique = true) })
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleMapping extends DefaultFields {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "userRoleMappingId")
	@JsonProperty("userRoleMappingId")
	private Long userRoleMappingId;

	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@ManyToOne
	@JsonProperty("userId")
	private User userId;

	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
	@ManyToOne
	@JsonProperty("roleId")
	private Role roleId;

	public UserRoleMapping() {
	}

	public Long getUserRoleMappingId() {
		return userRoleMappingId;
	}

	public void setUserRoleMappingId(Long userRoleMappingId) {
		this.userRoleMappingId = userRoleMappingId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRoleMapping [userRoleMappingId=" + userRoleMappingId + ", userId=" + userId + ", roleId=" + roleId
				+ "]";
	}

}
