/**
 * 
 */
package com.davos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.davos.dto.RoleDTO;
import com.davos.service.RoleService;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
@RestController
@RequestMapping(value = "/role/")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "role-create", method = RequestMethod.GET)
	public ModelAndView roleCreateScreen() {
		ModelAndView modelAndView = new ModelAndView("role/role-create");
		return modelAndView;
	}

	@RequestMapping(value = "role-list", method = RequestMethod.GET)
	public ModelAndView roleListScreen() {
		ModelAndView modelAndView = new ModelAndView("role/role-list");
		return modelAndView;
	}

	@RequestMapping(value = "findByRoleName")
	public Boolean findByRoleName(@RequestParam(name = "roleName") String roleName) {
		if (roleService.findByRoleName(roleName) != null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@RequestMapping(value = "createRole")
	public ReturnStatus createRole(@RequestBody RoleDTO roleDTO) {
		return roleService.addRole(roleDTO);
	}

	@RequestMapping(value = "listAllRoles")
	public List<RoleDTO> listAllRoles() {
		return roleService.getAllRoles();
	}
}
