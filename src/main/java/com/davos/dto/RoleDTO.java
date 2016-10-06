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
public class RoleDTO extends DefaultFields {

	private static final long serialVersionUID = 1L;
	@JsonProperty("roleId")
	private Long roleId;
	@JsonProperty("roleName")
	private String roleName;

	/**
	 * 
	 */
	public RoleDTO() {
	}

	/**
	 * @param roleId
	 * @param roleName
	 */
	public RoleDTO(Long roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
