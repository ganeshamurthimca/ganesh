/**
 * 
 */
package com.davos.dto;

import java.util.Set;

import com.davos.domain.Menu;

/**
 * @author vigneshwaran.b
 *
 */
public class MenuDTO extends DefaultFields {
	private static final long serialVersionUID = 1L;
	private Long menuId;
	private String menuName;
	private String menuLink;
	private String menuIcon;
	private Integer menuOrder;
	private MenuDTO menuParent;
	private Set<Menu> menuList;

	public Set<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(Set<Menu> menuList) {
		this.menuList = menuList;
	}

	public MenuDTO() {
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public MenuDTO getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(MenuDTO menuParent) {
		this.menuParent = menuParent;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", menuLink=" + menuLink + ", menuIcon=" + menuIcon
				+ ", menuOrder=" + menuOrder + "]";
	}
}
