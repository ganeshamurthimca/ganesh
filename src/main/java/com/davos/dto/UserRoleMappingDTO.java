/**
 * 
 */
package com.davos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleMappingDTO extends DefaultFields {

	private static final long serialVersionUID = 1L;
	@JsonProperty("userRoleMappingId")
	private Long userRoleMappingId;
	@JsonProperty("userId")
	private UserDTO userId;
	@JsonProperty("roleId")
	private RoleDTO roleId;

	/**
	 * 
	 */
	public UserRoleMappingDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getUserRoleMappingId() {
		return userRoleMappingId;
	}

	public void setUserRoleMappingId(Long userRoleMappingId) {
		this.userRoleMappingId = userRoleMappingId;
	}

	public UserDTO getUserDTO() {
		return userId;
	}

	public void setUserDTO(UserDTO userId) {
		this.userId = userId;
	}

	public RoleDTO getRoleDTO() {
		return roleId;
	}

	public void setRoleDTO(RoleDTO roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRoleMappingDTO [userRoleMappingId=" + userRoleMappingId + ", userDTO=" + userId + ", roleDTO="
				+ roleId + "]";
	}

}
