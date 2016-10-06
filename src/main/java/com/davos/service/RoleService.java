/**
 * 
 */
package com.davos.service;

import java.util.List;

import com.davos.dto.RoleDTO;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
public interface RoleService {
	public ReturnStatus addRole(RoleDTO roleDTO);

	public List<RoleDTO> getAllRoles();

	public RoleDTO findByRoleName(String roleName);
}
