/**
 * 
 */
package com.davos.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.davos.domain.User;
import com.davos.domain.UserAttempts;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Long> {
	@Modifying
	@Query("UPDATE UserAttempts ua SET ua.attempts = 0, ua.lastModified = null WHERE ua.userId.userId = ?1")
	int resetFailAttempts(long userId);

	@Transactional(readOnly = true)
	UserAttempts findByUserId(User user);

	@Modifying
	@Query("UPDATE UserAttempts ua SET ua.attempts = ?1, ua.lastModified = ?2 WHERE ua.userId.userId = ?3")
	int updateAttempts(int attempts, Date lastModified, long userId);
}
