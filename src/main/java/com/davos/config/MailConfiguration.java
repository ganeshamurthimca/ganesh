/**
 * 
 */
package com.davos.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.davos.mail.MailSend;

/**
 * @author vigneshwaran.b
 *
 */
@Configuration
@PropertySource(value = { "classpath:config/mail.properties" })
public class MailConfiguration {

	@Autowired
	private Environment env;

	private static final String PROPERTY_MAIL_HOST = "mail.host";
	private static final String PROPERTY_MAIL_PROTOCOL = "mail.protocol";
	private static final String PROPERTY_MAIL_PORT = "mail.smtp.port";

	// private static final String PROPERTY_SMTP

	@Bean(name = "mailSender")
	public MailSender mailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(env.getProperty(PROPERTY_MAIL_HOST));
		javaMailSenderImpl.setProtocol(env.getProperty(PROPERTY_MAIL_PROTOCOL));
		javaMailSenderImpl.setJavaMailProperties(getProperties());
		return javaMailSenderImpl;
	}

	public Properties getProperties() {
		Properties properties = System.getProperties();
		properties.setProperty(PROPERTY_MAIL_PORT, env.getProperty(PROPERTY_MAIL_PROTOCOL));
		return properties;

	}

	@Bean
	public MailSend mailSend() {
		return new MailSend();
	}
}
