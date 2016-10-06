/**
 * 
 */
package com.davos.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.davos.dto.UserDTO;
import com.davos.service.UserAttemptsSerivce;
import com.davos.service.UserService;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAttemptsSerivce userAttemptsSerivce;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "user-create", method = RequestMethod.GET)
	public ModelAndView userCreateScreen() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/user-create");
		return modelAndView;
	}

	@RequestMapping(value = "user-list", method = RequestMethod.GET)
	public ModelAndView userListScreen() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/user-list");
		return modelAndView;
	}

	@RequestMapping(value = "user-history", method = RequestMethod.GET)
	public ModelAndView userHistoryScreen() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/user-history");
		return modelAndView;
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public ReturnStatus createUser(@RequestBody UserDTO userDTO) {
		try {
			userDTO.setLastPasswordResetOn(new Date());
			return userService.addUser(userDTO);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ReturnStatus.FAILURE;
	}

	@RequestMapping(value = "listAllUsers", method = RequestMethod.GET)
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "findByUserName")
	public Boolean findByUserName(@RequestParam(name = "userName") String userName) {
		if (userService.findByUserName(userName) != null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@RequestMapping(value = "validatePassword")
	public Boolean validatePassword(@RequestParam(name = "userPassword") String userPassword) {
		Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");
		Matcher matcher = pattern.matcher(userPassword);
		return matcher.matches();
	}

	@RequestMapping(value = "resetPassword/{userId}")
	public String resetPassword(@PathVariable Long userId) {
		return "{\"response\":\"" + userService.resetPassword(userId) + "\"}";
	}

	@RequestMapping(value = "changeStatus/{userId}")
	public String changeStatus(@PathVariable Long userId) {
		return "{\"response\":\"" + userService.changeStatus(userId) + "\"}";
	}

	@RequestMapping(value = "listUserHistory/{userId}")
	public List<UserDTO> listUserHistory(@PathVariable Long userId) {
		return userService.listAllRevision(userId);
	}

	@RequestMapping(value = "unlockAccount/{userName:.+}")
	public String unlockAccount(@PathVariable String userName) {
		userAttemptsSerivce.resetFailAttempts(userName);
		return "{\"response\":\"Account Unlocked\"}";
	}

	@RequestMapping(value = "getCurrentUser")
	public String getCurrentUser(Principal principal) {
		String userName = principal.getName();
		return "{\"userName\":\"" + userName + "\"}";
	}
}
