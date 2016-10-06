/**
 * 
 */
package com.davos.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.davos.dto.UserAttemptsDTO;
import com.davos.service.UserAttemptsSerivce;

/**
 * @author vigneshwaran.b
 *
 */
@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {
	@Autowired
	private UserAttemptsSerivce userAttemptsSerivce;

	// @Autowired
	// private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	@Qualifier(value = "userSecurityService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Autowired
	@Qualifier(value = "passwordEncoder")
	@Override
	public void setPasswordEncoder(Object passwordEncoder) {
		super.setPasswordEncoder(passwordEncoder);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			Authentication auth = super.authenticate(authentication);
			userAttemptsSerivce.resetFailAttempts(authentication.getName());
			return auth;
		} catch (BadCredentialsException ex) {
			userAttemptsSerivce.updateFailAttempts(authentication.getName());
			throw ex;
		} catch (LockedException ex) {
			String error = "";
			UserAttemptsDTO userAttemptsDTO = userAttemptsSerivce.findByUserName(authentication.getName());
			if (userAttemptsDTO != null) {
				error = "User Account is locked! <br/> Username : " + authentication.getName();
			} else {
				error = ex.getMessage();
			}
			// try {
			// Scheduler scheduler = schedulerFactoryBean.getScheduler();
			// System.out.println("Scheduler name: " +
			// scheduler.getSchedulerName());
			// System.out.println("quartz controller looking for jobs...");
			// System.out.println("Found " + scheduler.getJobGroupNames().size()
			// + " job group names.");
			// for (String groupName : scheduler.getJobGroupNames()) {
			// System.out.println(groupName);
			// for (JobKey jobKey :
			// scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
			// scheduler.triggerJob(jobKey);
			// }
			// }
			// } catch (Exception e) {
			// System.out.println(e);
			// }
			throw new LockedException(error);
		}
	}

}
