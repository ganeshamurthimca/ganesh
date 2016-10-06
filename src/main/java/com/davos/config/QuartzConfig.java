/**
 * 
 */
package com.davos.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.davos.scheduler.PasswordResetScheduler;
import com.davos.service.UserService;

/**
 * @author vigneshwaran.b
 *
 */
@Configuration
public class QuartzConfig {

	// @Bean
	// public MethodInvokingJobDetailFactoryBean
	// methodInvokingJobDetailFactoryBean() {
	// MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean =
	// new MethodInvokingJobDetailFactoryBean();
	// methodInvokingJobDetailFactoryBean.setTargetBeanName("jobone");
	// methodInvokingJobDetailFactoryBean.setTargetMethod("myTask");
	//
	// return methodInvokingJobDetailFactoryBean;
	// }
	//
	// // Job is scheduled for 3+1 times with the interval of 30 seconds
	// @Bean
	// public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
	// SimpleTriggerFactoryBean simpleTriggerFactoryBean = new
	// SimpleTriggerFactoryBean();
	// simpleTriggerFactoryBean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
	// simpleTriggerFactoryBean.setStartDelay(3000);
	// simpleTriggerFactoryBean.setRepeatInterval(30000);
	// simpleTriggerFactoryBean.setRepeatCount(3);
	//
	// return simpleTriggerFactoryBean;
	// }
	//
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(UserService userService) {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(PasswordResetScheduler.class);
		Map<String, Object> map = new HashMap<>();
		map.put("userService", userService);
		jobDetailFactoryBean.setJobDataAsMap(map);
		jobDetailFactoryBean.setGroup("myGroup");
		jobDetailFactoryBean.setName("myJob");

		return jobDetailFactoryBean;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(UserService userService) {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean(userService).getObject());
		cronTriggerFactoryBean.setStartDelay(3000);
		cronTriggerFactoryBean.setName("MyTrigger");
		cronTriggerFactoryBean.setGroup("myGroup");
		cronTriggerFactoryBean.setCronExpression("0 0 0 * * ? *");

		return cronTriggerFactoryBean;
	}

	@Bean
	@Autowired
	public SchedulerFactoryBean schedulerFactoryBean(UserService userService) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setTriggers(cronTriggerFactoryBean(userService).getObject());

		return schedulerFactoryBean;
	}
}
