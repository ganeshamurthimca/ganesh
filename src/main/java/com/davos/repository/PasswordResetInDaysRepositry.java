/**
 * 
 */
package com.davos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davos.domain.PasswordResetInDays;

/**
 * @author vigneshwaran.b
 *
 */
public interface PasswordResetInDaysRepositry extends JpaRepository<PasswordResetInDays, Long> {

}
