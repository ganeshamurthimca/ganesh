/**
 * 
 */
package com.davos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.davos.domain.Menu;

/**
 * @author vigneshwaran.b
 *
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
	@Transactional(readOnly = true)
	@Query("SELECT m FROM Menu m WHERE m.menuId IN (:menuId) AND m.menuParent IS NULL AND m.isEnabled = true")
	public List<Menu> find(@Param("menuId") List<Long> menuId);

	@Transactional(readOnly = true)
	public List<Menu> findByMenuParentIsNull();

	@Transactional(readOnly = true)
	public List<Menu> findByMenuParent(Menu menuParent);
}
