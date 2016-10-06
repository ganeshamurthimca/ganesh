/**
 * 
 */
package com.davos.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@Entity
@Table(name = "menu", indexes = @Index(columnList = "menuName,menuLink,menuOrder", unique = true))
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu extends DefaultFields {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "menuId")
	@JsonProperty(value = "menuId")
	private Long menuId;
	@Column(name = "menuName", nullable = false)
	@JsonProperty(value = "menuName")
	private String menuName;
	@Column(name = "menuLink")
	@JsonProperty(value = "menuLink")
	private String menuLink;
	@Column(name = "menuIcon")
	@JsonProperty(value = "menuIcon")
	private String menuIcon;
	@Column(name = "menuOrder")
	@JsonProperty(value = "menuOrder")
	private Integer menuOrder;

	@JoinColumn(name = "menuParent", referencedColumnName = "menuId")
	@ManyToOne
	@JsonBackReference
	private Menu menuParent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "menuParent")
	private Set<Menu> menuList;

	public Set<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(Set<Menu> menuList) {
		this.menuList = menuList;
	}

	public Menu() {
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

	public Menu getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Menu menuParent) {
		this.menuParent = menuParent;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", menuLink=" + menuLink + ", menuIcon=" + menuIcon
				+ ", menuOrder=" + menuOrder + "]";
	}

}
