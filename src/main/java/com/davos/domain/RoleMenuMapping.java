/**
 * 
 */
package com.davos.domain;

import javax.persistence.Basic;
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
@Table(name = "Role_Menu_Mapping", indexes = @Index(columnList = "menuId,roleId"))
@JsonIgnoreProperties(ignoreUnknown = false)
public class RoleMenuMapping extends DefaultFields {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@JsonProperty(value = "roleMenuMappingId")
	private Long roleMenuMappingId;

	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
	@ManyToOne
	@JsonProperty(value = "roleId")
	private Role roleId;

	@JoinColumn(name = "menuId", referencedColumnName = "menuId")
	@ManyToOne
	@JsonProperty(value = "menuId")
	private Menu menuId;

	public RoleMenuMapping() {
	}

	public Long getMenuRoleMappingId() {
		return roleMenuMappingId;
	}

	public void setMenuRoleMappingId(Long roleMenuMappingId) {
		this.roleMenuMappingId = roleMenuMappingId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Menu getMenuId() {
		return menuId;
	}

	public void setMenuId(Menu menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenuMapping [roleMenuMappingId=" + roleMenuMappingId + ", roleId=" + roleId + ", menuId=" + menuId
				+ "]";
	}

}
