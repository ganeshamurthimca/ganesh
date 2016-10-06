/**
 * 
 */
package com.davos.service;

import java.util.List;

import com.davos.dto.UserDTO;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserService {
	ReturnStatus addUser(UserDTO userDTO);

	// ReturnStatus updateUser(UserDTO userDTO);

	List<UserDTO> getAllUsers();

	UserDTO findByUserName(String userName);

	List<UserDTO> listAllRevision(Long userId);

	String resetPassword(Long userId);

	String changeStatus(Long userId);

	void setCredentialExpired();
}
