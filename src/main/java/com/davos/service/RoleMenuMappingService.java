package com.davos.service;

import java.util.List;

import com.davos.dto.MenuDTO;
import com.davos.dto.RoleDTO;
import com.davos.dto.RoleMenuMappingDTO;
import com.davos.status.ReturnStatus;

public interface RoleMenuMappingService {

	public ReturnStatus addRoleMenu(RoleMenuMappingDTO roleMenuMappingDTO);

	public List<MenuDTO> findByRoleId(RoleDTO roleDTO);

	public List<MenuDTO> findByUserId(String userName);

	public List<RoleMenuMappingDTO> listAllRoleMenuMappings();

}
