/**
 * 
 */
package com.davos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davos.domain.User;
import com.davos.domain.UserRoleMapping;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long> {
	public List<UserRoleMapping> findByUserId(User user);
}
