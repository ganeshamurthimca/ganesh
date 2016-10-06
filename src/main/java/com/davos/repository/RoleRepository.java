/**
 * 
 */
package com.davos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davos.domain.Role;

/**
 * @author vigneshwaran.b
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByRoleName(String roleName);
}
