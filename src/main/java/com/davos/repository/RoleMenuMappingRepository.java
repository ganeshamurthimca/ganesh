package com.davos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davos.domain.Role;
import com.davos.domain.RoleMenuMapping;

public interface RoleMenuMappingRepository extends JpaRepository<RoleMenuMapping, Long> {
	public List<RoleMenuMapping> findByRoleId(Role role);

	public List<RoleMenuMapping> findByRoleIdIn(List<Role> roleList);
}
