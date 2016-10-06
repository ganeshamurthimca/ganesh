/**
 * 
 */
package com.davos.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.davos.domain.Role;
import com.davos.dto.RoleDTO;
import com.davos.dto.UserDTO;
import com.davos.repository.RoleRepository;
import com.davos.service.RoleService;
import com.davos.service.UserService;
import com.davos.status.ReturnStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public ReturnStatus addRole(RoleDTO roleDTO) {
		try {
			if (roleDTO.getRoleId() == null) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Date currentDate = new Date();
				UserDTO userDTO = userService.findByUserName(authentication.getName());
				roleDTO.setActionBy(userDTO);
				roleDTO.setActionOn(currentDate);
			}
			Role role = objectMapper.readValue(objectMapper.writeValueAsString(roleDTO), Role.class);
			if (roleRepository.save(role) != null) {
				return ReturnStatus.SUCCESS;
			}
		} catch (DataIntegrityViolationException ex) {
			logger.error(ex.getMessage());
			return ReturnStatus.ALREADYEXISTS;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ReturnStatus.FAILURE;
		}
		return ReturnStatus.OTHER;
	}

	@Override
	public List<RoleDTO> getAllRoles() {
		List<RoleDTO> roleDTOs = new ArrayList<>();
		try {
			List<Role> roles = roleRepository.findAll();
			roles.forEach(role -> {
				try {
					roleDTOs.add(objectMapper.readValue(objectMapper.writeValueAsString(role), RoleDTO.class));
				} catch (IOException ex) {
					logger.error(ex.toString());
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return roleDTOs;
		}
		return roleDTOs;
	}

	@Override
	public RoleDTO findByRoleName(String roleName) {
		try {
			Role role = roleRepository.findByRoleName(roleName);
			if (role != null) {
				RoleDTO roleDTO = objectMapper.readValue(objectMapper.writeValueAsString(role), RoleDTO.class);
				return roleDTO;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

}
