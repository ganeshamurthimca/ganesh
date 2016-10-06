/**
 * 
 */
package com.davos.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.davos.dto.UserDTO;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserSecurityService extends UserDetailsService {
	List<GrantedAuthority> getGrantedAuthorities(UserDTO userDTO);
}
