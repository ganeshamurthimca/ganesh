/**
 * 
 */
package com.davos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author vigneshwaran.b
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleMenuMappingDTO extends DefaultFields {
	private static final long serialVersionUID = 1L;

	private Long roleMenuMappingId;
	private RoleDTO roleId;
	private MenuDTO menuId;

	public RoleMenuMappingDTO() {
	}

	public Long getRoleMenuMappingId() {
		return roleMenuMappingId;
	}

	public void setRoleMenuMappingId(Long roleMenuMappingId) {
		this.roleMenuMappingId = roleMenuMappingId;
	}

	public RoleDTO getRoleId() {
		return roleId;
	}

	public void setRoleId(RoleDTO roleId) {
		this.roleId = roleId;
	}

	public MenuDTO getMenuId() {
		return menuId;
	}

	public void setMenuId(MenuDTO menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenuMappingDTO [roleMenuMappingId=" + roleMenuMappingId + ", roleId=" + roleId + ", menuId="
				+ menuId + "]";
	}

}
