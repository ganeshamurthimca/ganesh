/**
 * 
 */
package com.davos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davos.dto.MenuDTO;
import com.davos.dto.RoleDTO;
import com.davos.service.RoleMenuMappingService;

/**
 * @author vigneshwaran.b
 *
 */
@RestController
@RequestMapping(value = "/RoleMenuMapping/")
public class RoleMenuMappingController {
	@Autowired
	private RoleMenuMappingService roleMenuMappingService;

	@RequestMapping(value = "findByUserName/{userName:.+}")
	public List<MenuDTO> findByUserName(@PathVariable String userName) {
		return roleMenuMappingService.findByUserId(userName);
	}

	@RequestMapping(value = "findByRoleId/{roleId}")
	public List<MenuDTO> findByRoleId(@PathVariable Long roleId) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleId(roleId);
		return roleMenuMappingService.findByRoleId(roleDTO);
	}

}
