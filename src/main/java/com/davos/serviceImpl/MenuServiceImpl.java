/**
 * 
 */
package com.davos.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
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

import com.davos.domain.Menu;
import com.davos.dto.MenuDTO;
import com.davos.dto.UserDTO;
import com.davos.repository.MenuRepository;
import com.davos.service.MenuService;
import com.davos.service.UserService;
import com.davos.status.ReturnStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Service(value = "menuService")
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ReturnStatus addMenu(MenuDTO menuDTO) {
		try {
			if (menuDTO.getMenuId() == null) {
				Date currentDate = new Date();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				UserDTO actionBy = userService.findByUserName(authentication.getName());
				menuDTO.setActionBy(actionBy);
				menuDTO.setActionOn(currentDate);
			}
			menuDTO.setMenuList(null);// Avoid manipulating child element
			Menu menu = objectMapper.readValue(objectMapper.writeValueAsString(menuDTO), Menu.class);
			if (menuRepository.save(menu) != null) {
				return ReturnStatus.SUCCESS;
			}
		} catch (DataIntegrityViolationException ex) {
			logger.error("Menu Name already exists " + menuDTO.getMenuName());
			return ReturnStatus.ALREADYEXISTS;
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ReturnStatus.FAILURE;
		}
		return ReturnStatus.OTHER;
	}

	@Override
	public List<MenuDTO> listAllMenus() {
		try {
			List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
			List<Menu> menuList = menuRepository.findAll();
			menuList.forEach(menu -> {
				try {
					menuDTOList.add(objectMapper.readValue(objectMapper.writeValueAsString(menu), MenuDTO.class));
				} catch (Exception ex) {
					logger.error(ex.toString());
				}
			});

			return menuDTOList;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}

	@Override
	public List<MenuDTO> listAllParentMenus(List<Long> menuList1) {
		try {
			List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
			List<Menu> menuList = menuRepository.find(menuList1);
			menuList.forEach(menu -> {
				try {
					menuDTOList.add(objectMapper.readValue(objectMapper.writeValueAsString(menu), MenuDTO.class));
				} catch (Exception ex) {
					logger.error(ex.toString());
				}
			});
			return menuDTOList;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}

	@Override
	public List<MenuDTO> findByParentId(MenuDTO menuDTO) {
		try {
			List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
			Menu menuObject = objectMapper.readValue(objectMapper.writeValueAsString(menuDTO), Menu.class);
			List<Menu> menuList = menuRepository.findByMenuParent(menuObject);
			menuList.forEach(menu -> {
				try {
					menuDTOList.add(objectMapper.readValue(objectMapper.writeValueAsString(menu), MenuDTO.class));
				} catch (Exception ex) {
					logger.error(ex.toString());
				}
			});
			return menuDTOList;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}

	@Override
	public List<MenuDTO> findByParentMenu() {
		try {
			List<Menu> menuList = menuRepository.findByMenuParentIsNull();
			List<MenuDTO> menuDTOList = new LinkedList<>();
			menuList.forEach(menu -> {
				try {
					menuDTOList.add(objectMapper.readValue(objectMapper.writeValueAsString(menu), MenuDTO.class));
				} catch (IOException ex) {
					logger.error(ex.toString());
				}
			});

			return menuDTOList;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}

}
