/**
 * 
 */
package com.davos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.davos.dto.MenuDTO;
import com.davos.service.MenuService;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
@RestController
@RequestMapping(value = "/menu/")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "menu-create", method = RequestMethod.GET)
	public ModelAndView menuCreateScreen() {
		ModelAndView modelAndView = new ModelAndView("menu/menu-create");
		return modelAndView;
	}

	@RequestMapping(value = "menu-list", method = RequestMethod.GET)
	public ModelAndView menuListScreen() {
		ModelAndView modelAndView = new ModelAndView("menu/menu-list");
		return modelAndView;
	}

	@RequestMapping(value = "addMenu", method = RequestMethod.POST)
	public ReturnStatus addMenu(@RequestBody MenuDTO menuDTO) {		
		return menuService.addMenu(menuDTO);
	}

	@RequestMapping(value = "populateMenu")
	public List<MenuDTO> populateMenu() {
		return menuService.listAllMenus();
	}

	@RequestMapping(value = "populateParentMenu")
	public List<MenuDTO> populateParentMenu() {
		return menuService.findByParentMenu();
	}
}
