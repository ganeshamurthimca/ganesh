/**
 * 
 */
package com.davos.service;

import java.util.List;

import com.davos.dto.UserDTO;
import com.davos.dto.UserRoleMappingDTO;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserRoleMappingService {
	public ReturnStatus addUserRoleMapping(UserRoleMappingDTO userRoleMappingDTO);

	public List<UserRoleMappingDTO> getAllUserRoleMappingDTO();

	public List<UserRoleMappingDTO> findByUserId(UserDTO userDTO);
}
