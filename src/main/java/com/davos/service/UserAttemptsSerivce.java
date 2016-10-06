/**
 * 
 */
package com.davos.service;

import com.davos.dto.UserAttemptsDTO;

/**
 * @author vigneshwaran.b
 *
 */
public interface UserAttemptsSerivce {
	public void updateFailAttempts(String userName);

	public UserAttemptsDTO findByUserName(String userName);

	public void resetFailAttempts(String userName);

	public Boolean isUserExists(String userName);

	public Boolean unLockAccounts();
}
