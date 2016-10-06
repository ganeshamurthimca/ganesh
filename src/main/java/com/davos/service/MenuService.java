/**
 * 
 */
package com.davos.service;

import java.util.List;

import com.davos.dto.MenuDTO;
import com.davos.status.ReturnStatus;

/**
 * @author vigneshwaran.b
 *
 */
public interface MenuService {
	public ReturnStatus addMenu(MenuDTO menuDTO);

	public List<MenuDTO> listAllMenus();

	public List<MenuDTO> listAllParentMenus(List<Long> menuList);

	public List<MenuDTO> findByParentId(MenuDTO menuDTO);

	public List<MenuDTO> findByParentMenu();

}
