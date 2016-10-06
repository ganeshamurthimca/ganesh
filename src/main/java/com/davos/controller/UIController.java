/**
 * 
 */
package com.davos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vigneshwaran.b
 *
 */
@RestController
@PropertySource(value = { "classpath:config/resetAccount.properties" })
public class UIController {
	@Autowired
	private Environment env;

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView welcome() {
		return new ModelAndView("common/index");
	}

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		return new ModelAndView("common/home");
	}

	@RequestMapping(value = "/common")
	public ModelAndView common() {
		return new ModelAndView("common/common");
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout, HttpServletRequest request) {
		if (error != null) {
			model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			if (exception instanceof LockedException) {
				if (env.getProperty("reset.account.after.in.minutes", Long.class) != null) {
					model.addAttribute("minute", env.getProperty("reset.account.after.in.minutes"));
				} else {
					model.addAttribute("minute", 5);
				}
				if (env.getProperty("reset.account.after.in.seconds", Long.class) != null) {
					model.addAttribute("seconds", env.getProperty("reset.account.after.in.seconds"));
				} else {
					model.addAttribute("seconds", 0);
				}
			}
		}
		if (error == null && logout != null) {
			model.addAttribute("logout", "You were logged out successfully");
		}
		return new ModelAndView("common/login");
	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password";
		}
		return error;
	}
}