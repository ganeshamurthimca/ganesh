/**
 * 
 */
package com.davos.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.davos.service.UserService;

/**
 * @author vigneshwaran.b
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PasswordResetScheduler extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		UserService userService = (UserService) jobDataMap.get("userService");
		userService.setCredentialExpired();
	}

	public void setName(String name) {

	}
}
