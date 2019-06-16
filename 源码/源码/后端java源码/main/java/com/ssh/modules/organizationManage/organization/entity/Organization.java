/**  
 * @Title: Organization.java  
 * @Package com.ssh.entity  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.organizationManage.organization.entity;

import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: Organization
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:04:56
 */

@Entity
@Table(name = "organization")
public class Organization {
	private int id;
	private String name;
	private String description;
	private String attribute;
	private int parentId;
	private boolean open;
	private int deep;
	private Vector<Organization> organization;
	private Vector<Integer> childIds;
	
	public Organization(int id, String name, String description, String attribute, int parentId, int deep) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.attribute = attribute;
		this.parentId = parentId;
		this.open = true;
		this.deep = deep;
		
		organization = new Vector<Organization>();
		childIds = new Vector<Integer>();
	}
	public Organization() {
		this.open = true;
		// TODO Auto-generated constructor stub
	}
	public static Organization OrgToOrganization(Org org) {
		Organization organization = new Organization(
				org.getId(),org.getName(),org.getDescription(),org.getAttribute(),org.getParentId(),org.getDeep());
		return organization;
		
	}
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description", nullable = false, length = 32)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "attribute", nullable = false, length = 32)
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	@Column(name = "parentId", nullable = false, length = 32)
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Vector<Organization> getOrganization() {
		return organization;
	}
	public void setOrganization(Vector<Organization> organization) {
		this.organization = organization;
	}
	public Vector<Integer> getChildIds() {
		return childIds;
	}
	public void setChildIds(Vector<Integer> childIds) {
		this.childIds = childIds;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}

}
