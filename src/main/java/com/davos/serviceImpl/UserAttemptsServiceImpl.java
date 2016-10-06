/**
 * 
 */
package com.davos.serviceImpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;

import com.davos.domain.User;
import com.davos.domain.UserAttempts;
import com.davos.dto.UserAttemptsDTO;
import com.davos.dto.UserDTO;
import com.davos.repository.UserAttemptsRepository;
import com.davos.service.UserAttemptsSerivce;
import com.davos.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@PropertySource(value = { "classpath:config/login_attempts.properties" })
@Service
public class UserAttemptsServiceImpl implements UserAttemptsSerivce {
	@Autowired
	private Environment env;
	@Autowired
	private UserAttemptsRepository userAttemptsRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserAttemptsServiceImpl.class);
	private static final String MAX_ATTEMPTS = "max.attempts";

	@Override
	public void updateFailAttempts(String userName) {
		try {
			UserAttemptsDTO userAttemptsDTO = findByUserName(userName);
			if (userAttemptsDTO == null) {
				if (isUserExists(userName)) {
					userAttemptsDTO = new UserAttemptsDTO();
					userAttemptsDTO.setAttempts(Byte.parseByte("1"));
					userAttemptsDTO.setLastModified(new Date());
					userAttemptsDTO.setUserId(userService.findByUserName(userName));
					UserAttempts userAttempts = objectMapper.readValue(objectMapper.writeValueAsString(userAttemptsDTO),
							UserAttempts.class);
					userAttemptsRepository.save(userAttempts);
				}
			} else {
				if (isUserExists(userName)) {
					userAttemptsDTO.setAttempts(userAttemptsDTO.getAttempts() + 1);
					userAttemptsDTO.setLastModified(new Date());
					UserAttempts userAttempts = objectMapper.readValue(objectMapper.writeValueAsString(userAttemptsDTO),
							UserAttempts.class);
					userAttemptsRepository.save(userAttempts);
				}

				if (userAttemptsDTO.getAttempts() >= Integer.parseInt(env.getProperty(MAX_ATTEMPTS))) {
					UserDTO userDTO = userService.findByUserName(userName);
					userDTO.setAccountNonLocked(false);
					userService.addUser(userDTO);
					throw new LockedException("User Account is Locked");
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public UserAttemptsDTO findByUserName(String userName) {
		try {
			UserDTO userDTO = userService.findByUserName(userName);
			if (isUserExists(userName)) {
				String userDtoString = objectMapper.writeValueAsString(userDTO);
				User user = objectMapper.readValue(userDtoString, User.class);
				UserAttempts userAttempts = userAttemptsRepository.findByUserId(user);
				if (userAttempts == null) {
					return null;
				}
				UserAttemptsDTO userAttemptsDTO = objectMapper.readValue(objectMapper.writeValueAsString(userAttempts),
						UserAttemptsDTO.class);

				return userAttemptsDTO;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public void resetFailAttempts(String userName) {
		try {
			if (isUserExists(userName)) {
				UserAttemptsDTO userAttemptsDTO = findByUserName(userName);
				if (userAttemptsDTO != null) {
					userAttemptsDTO.setLastModified(null);
					userAttemptsDTO.setAttempts(0);
					UserAttempts userAttempts = objectMapper.readValue(objectMapper.writeValueAsString(userAttemptsDTO),
							UserAttempts.class);
					userAttemptsRepository.save(userAttempts);
					UserDTO userDTO = userService.findByUserName(userName);
					userDTO.setAccountNonLocked(Boolean.TRUE);
					userService.addUser(userDTO);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public Boolean isUserExists(String userName) {
		try {
			if (userService.findByUserName(userName) != null) {
				return Boolean.TRUE;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean unLockAccounts() {
		System.out.println("Inside unlock Accounts" + new Date());
		return Boolean.FALSE;
	}
}
