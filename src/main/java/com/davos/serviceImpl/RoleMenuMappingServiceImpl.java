/**
 * 
 */
package com.davos.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.davos.domain.Role;
import com.davos.domain.RoleMenuMapping;
import com.davos.dto.MenuDTO;
import com.davos.dto.RoleDTO;
import com.davos.dto.RoleMenuMappingDTO;
import com.davos.dto.UserDTO;
import com.davos.dto.UserRoleMappingDTO;
import com.davos.repository.RoleMenuMappingRepository;
import com.davos.service.MenuService;
import com.davos.service.RoleMenuMappingService;
import com.davos.service.UserRoleMappingService;
import com.davos.service.UserService;
import com.davos.status.ReturnStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Service
public class RoleMenuMappingServiceImpl implements RoleMenuMappingService {

	@Autowired
	private RoleMenuMappingRepository roleMenuMappingRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleMappingService userRoleMappingService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MenuService menuService;

	private static final Logger logger = LoggerFactory.getLogger(RoleMenuMappingServiceImpl.class);

	@Override
	public ReturnStatus addRoleMenu(RoleMenuMappingDTO roleMenuMappingDTO) {
		try {
			if (roleMenuMappingDTO.getRoleMenuMappingId() == null) {
				Date currentDate = new Date();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				UserDTO actionBy = userService.findByUserName(authentication.getName());
				roleMenuMappingDTO.setActionBy(actionBy);
				roleMenuMappingDTO.setActionOn(currentDate);
			}
			RoleMenuMapping roleMenuMapping = objectMapper
					.readValue(objectMapper.writeValueAsString(roleMenuMappingDTO), RoleMenuMapping.class);
			if (roleMenuMappingRepository.save(roleMenuMapping) != null) {
				return ReturnStatus.SUCCESS;
			}
		} catch (DataIntegrityViolationException ex) {
			logger.error("Role Menu Mapping already done ");
			return ReturnStatus.ALREADYEXISTS;
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ReturnStatus.FAILURE;
		}
		return ReturnStatus.OTHER;

	}

	@Override
	public List<MenuDTO> findByRoleId(RoleDTO roleDTO) {
		try {
			Role role = objectMapper.readValue(objectMapper.writeValueAsString(roleDTO), Role.class);
			List<RoleMenuMapping> roleMenuMappingList = roleMenuMappingRepository.findByRoleId(role);
			List<Long> menuList = new LinkedList<>();
			roleMenuMappingList.forEach(action -> {
				menuList.add(action.getMenuId().getMenuId());
			});
			List<MenuDTO> menuDTOList = menuService.listAllParentMenus(menuList);
			return menuDTOList;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}

	@Override
	public List<MenuDTO> findByUserId(String userName) {
		UserDTO userDTO = userService.findByUserName(userName);
		List<UserRoleMappingDTO> userRoleMappingDTOList = userRoleMappingService.findByUserId(userDTO);
		List<Role> roleList = new LinkedList<>();
		List<Long> menuList = new LinkedList<>();
		userRoleMappingDTOList.forEach(userRoleMappingDTO -> {
			try {
				roleList.add(objectMapper.readValue(objectMapper.writeValueAsString(userRoleMappingDTO.getRoleDTO()),
						Role.class));
			} catch (IOException ex) {
				logger.error(ex.toString());
			}
		});
		List<RoleMenuMapping> roleMenuMappingList = roleMenuMappingRepository.findByRoleIdIn(roleList);
		roleMenuMappingList.forEach(action -> {
			menuList.add(action.getMenuId().getMenuId());
		});
		List<MenuDTO> menuDTOList = menuService.listAllParentMenus(menuList);
		return menuDTOList;
	}

	@Override
	public List<RoleMenuMappingDTO> listAllRoleMenuMappings() {
		List<RoleMenuMapping> roleMenuMappingList = roleMenuMappingRepository.findAll();
		List<RoleMenuMappingDTO> roleMenuMappingDTOList = new LinkedList<>();
		roleMenuMappingDTOList = convertDomainToDTO(roleMenuMappingList);
		return roleMenuMappingDTOList;
	}

	/*
	 * To convert from domain collection to DTO collection
	 */
	private List<RoleMenuMappingDTO> convertDomainToDTO(List<RoleMenuMapping> roleMenuMappingList) {
		List<RoleMenuMappingDTO> roleMenuMappingDTOList = new LinkedList<>();
		roleMenuMappingList.forEach(roleMenuMapping -> {
			try {
				System.out.println(roleMenuMapping);
				roleMenuMappingDTOList.add(objectMapper.readValue(objectMapper.writeValueAsString(roleMenuMapping),
						RoleMenuMappingDTO.class));
			} catch (IOException ex) {
				logger.error(ex.toString());
			}
		});
		return roleMenuMappingDTOList;
	}
}
