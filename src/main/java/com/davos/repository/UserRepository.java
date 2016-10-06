/**
 * 
 */
package com.davos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davos.domain.User;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
