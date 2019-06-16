package com.ssh.modules.organizationManage.role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: Authority
 * @Description: TODO
 * @author: Administrator
 * @date: 2019年4月13日 下午3:04:56
 */
@Entity
@Table(name = "authority")
public class Authority {
	private String id;
	private String name;
	private String underMoudle;
	public Authority(String id, String name,String underModules){
		this.id = id;
		this.name = name;
		this.underMoudle = underModules;
	}
	public Authority() {
		// TODO Auto-generated constructor stub
	}
	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "underMoudle", nullable = false, length = 32)
	public String getUnderMoudle() {
		return underMoudle;
	}
	public void setUnderMoudle(String underMoudle) {
		this.underMoudle = underMoudle;
	}

}
