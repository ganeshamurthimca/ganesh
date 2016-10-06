/**
 * 
 */
package com.davos.serviceImpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.davos.domain.PasswordResetInDays;
import com.davos.domain.User;
import com.davos.dto.UserDTO;
import com.davos.repository.PasswordResetInDaysRepositry;
import com.davos.service.PasswordResetInDaysService;
import com.davos.service.UserService;
import com.davos.status.ReturnStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Service(value = "passwordResetInDaysService")
public class PasswordResetInDaysServiceImpl implements PasswordResetInDaysService {
	@Autowired
	PasswordResetInDaysRepositry passwordResetInDaysRepositry;

	@Autowired
	@Lazy
	UserService userService;

	@Autowired
	ObjectMapper objectMapper;

	private Logger logger = LoggerFactory.getLogger(PasswordResetInDaysServiceImpl.class);

	@Override
	public Long getPasswordResetInDays() {
		try {
			PasswordResetInDays passwordResetInDays = passwordResetInDaysRepositry.findOne(1L);
			if (passwordResetInDays != null) {
				return passwordResetInDays.getPasswordResetInDays();
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return 60L;
	}

	@Override
	public ReturnStatus AddPasswordResetInDays(PasswordResetInDays passwordResetInDays) {
		try {
			Date date = new Date();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDTO userDTO = userService.findByUserName(authentication.getName());
			User user = objectMapper.readValue(objectMapper.writeValueAsString(userDTO), User.class);
			passwordResetInDays.setActionOn(date);
			passwordResetInDays.setActionBy(user);

			if (passwordResetInDaysRepositry.save(passwordResetInDays) != null) {
				return ReturnStatus.SUCCESS;
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ReturnStatus.FAILURE;
		}
		return ReturnStatus.OTHER;
	}
}
