/**
 * 
 */
package com.davos.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davos.domain.User;
import com.davos.dto.UserDTO;
import com.davos.repository.UserRepository;
import com.davos.service.PasswordResetInDaysService;
import com.davos.service.UserService;
import com.davos.status.ReturnStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier(value = "userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier(value = "passwordEncoder")
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordResetInDaysService passwordResetInDaysService;

	@PersistenceContext
	protected EntityManager entityManager;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public ReturnStatus addUser(UserDTO userDTO) {
		if (userDTO.getUserId() == null) {
			Date date = new Date();
			userDTO.setActionOn(date);
			userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
		}
		try {
			if (userDTO.getUserId() == null) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				userDTO.setActionBy(findByUserName(authentication.getName()));
			}
			User user = modelMapper.map(userDTO, User.class);
			if (userRepository.save(user) != null) {
				return ReturnStatus.SUCCESS;
			}
		} catch (DataIntegrityViolationException ex) {
			logger.error(ex.toString());
			return ReturnStatus.ALREADYEXISTS;
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ReturnStatus.FAILURE;
		}
		return ReturnStatus.OTHER;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		userList.forEach(user -> {
			try {
				userDTOList.add(objectMapper.readValue(objectMapper.writeValueAsString(user), UserDTO.class));
			} catch (IOException ex) {
				logger.error(ex.toString());
			}
		});
		return userDTOList;
	}

	@Override
	public UserDTO findByUserName(String userName) {
		try {
			User user = userRepository.findByUserName(userName);
			if (user != null) {
				UserDTO userDTO = modelMapper.map(user, UserDTO.class);
				return userDTO;
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}

	@Override
	public List<UserDTO> listAllRevision(Long userId) {
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		List<Number> revisions = auditReader.getRevisions(User.class, userId);
		List<UserDTO> userDTOList = new ArrayList<>();
		revisions.forEach(revisionNumber -> {
			User user = auditReader.find(User.class, userId, revisionNumber);
			try {
				userDTOList.add(modelMapper.map(user, UserDTO.class));
			} catch (Exception ex) {
				logger.error(ex.toString());
			}
		});
		return userDTOList;
	}

	@Override
	public String resetPassword(Long userId) {
		try {
			User user = userRepository.findOne(userId);
			if (user != null) {
				user.setLastPasswordResetOn(new Date());
				user.setUserPassword(passwordEncoder.encode("Pass123#d"));
				userRepository.save(user);
				return "Password reset successfully";
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return "Error Occured while trying to reset your password";
	}

	@Override
	public String changeStatus(Long userId) {
		try {
			User user = userRepository.findOne(userId);
			if (user != null) {
				if (user.getUserName().equalsIgnoreCase("admin")) {
					return "You dont have rights to change Admin Status";
				}
				user.setIsEnabled(!user.getIsEnabled());
				userRepository.save(user);
				return "Status changed successfully";
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return "Error Occured while trying to change status";
	}

	@Override
	@Async
	public void setCredentialExpired() {
		try {
			List<User> userList = userRepository.findAll();
			Date currentDate = new Date();
			userList.forEach(user -> {
				long differenceDate = currentDate.getTime() - user.getLastPasswordResetOn().getTime();
				long passwordInDays = passwordResetInDaysService.getPasswordResetInDays();
				long passwordInDaysDiff = TimeUnit.DAYS.convert(differenceDate, TimeUnit.MILLISECONDS);
				if (passwordInDays >= passwordInDaysDiff) {
					user.setCredentialsNonExpired(Boolean.FALSE);
					userRepository.save(user);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}
}
