package com.davos.service;

import com.davos.domain.PasswordResetInDays;
import com.davos.status.ReturnStatus;

public interface PasswordResetInDaysService {
	public Long getPasswordResetInDays();

	public ReturnStatus AddPasswordResetInDays(PasswordResetInDays passwordResetInDays);
}
