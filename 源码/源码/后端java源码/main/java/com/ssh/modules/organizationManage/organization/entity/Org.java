/**  
 * @Title: Person.java  
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
 * @ClassName: Persons
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:04:56
 */
@Entity
@Table(name = "org")
public class Org {

	private int id;
	private String name;
	private String description;
	private String attribute;
	private int parentId;
	private int deep;
	
	public Org() {}

	public Org(int id, String name, String description, String attribute, int parentId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.attribute = attribute;
		this.parentId = parentId;
		
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
	@Column(name = "deep", nullable = false, length = 32)
	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

}