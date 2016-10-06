/**
 * 
 */
package com.davos.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davos.dto.UserAttemptsDTO;
import com.davos.dto.UserDTO;
import com.davos.dto.UserRoleMappingDTO;
import com.davos.service.UserAttemptsSerivce;
import com.davos.service.UserRoleMappingService;
import com.davos.service.UserSecurityService;
import com.davos.service.UserService;



/**
 * @author vigneshwaran.b
 *
 */
@Service(value = "userSecurityService")
@PropertySource(value = { "classpath:config/resetAccount.properties" })
public class UserSecurityServiceImpl implements UserSecurityService {
	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	UserRoleMappingService userRoleMappingService;

	@Autowired
	UserAttemptsSerivce userAttemptsSerivce;

	private static final String RESET_ACCOUNT_AFTER_IN_MINUTES = "reset.account.after.in.minutes";

	private Logger logger = LoggerFactory.getLogger(UserSecurityServiceImpl.class);

	Long resetInMinutes = 5L;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {
			/*
			 * Checking whether User Account is Locked. If locked for a minute
			 * then automatically unlock the code
			 */
			UserAttemptsDTO userAttemptsDTO = userAttemptsSerivce.findByUserName(userName);
			if (userAttemptsDTO != null) {
				Date date = new Date();
				if (userAttemptsDTO.getLastModified() != null) {
					Long differenceTime = date.getTime() - userAttemptsDTO.getLastModified().getTime();
					Long diffMinutes = differenceTime / (60 * 1000) % 60;
					if (env.getProperty(RESET_ACCOUNT_AFTER_IN_MINUTES, Long.class) != null) {
						resetInMinutes = env.getProperty(RESET_ACCOUNT_AFTER_IN_MINUTES, Long.class);
					}
					if (diffMinutes >= resetInMinutes) {
						userAttemptsSerivce.resetFailAttempts(userName);
					}

				}
			} // ends Unlock user account code

			UserDTO userDTO = userService.findByUserName(userName);
			if (userDTO == null) {
				throw new UsernameNotFoundException("User Details not found");
			}
			return new org.springframework.security.core.userdetails.User(userDTO.getUserName(),
					userDTO.getUserPassword(), userDTO.getIsEnabled(), userDTO.getAccountNonExpired(),
					userDTO.getCredentialsNonExpired(), userDTO.getAccountNonLocked(), getGrantedAuthorities(userDTO));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public List<GrantedAuthority> getGrantedAuthorities(UserDTO userDTO) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			List<UserRoleMappingDTO> userRoleMappingDTOs = userRoleMappingService.findByUserId(userDTO);
			userRoleMappingDTOs.forEach(userRoleMappingDTO -> authorities
					.add(new SimpleGrantedAuthority("ROLE_" + userRoleMappingDTO.getRoleDTO().getRoleName())));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return authorities;
	}

}
