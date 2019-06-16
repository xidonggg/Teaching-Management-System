package com.ssh.modules.organizationManage.role.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "role")
public class Role {
	private String id;
	private String name;
	private List<Authority> authorities;
	public Role(String id, String name, List<Authority> authorities){
		this.id = id;
		this.name = name;
		this.authorities = authorities;
	}
	public Role() {}
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
	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(fetch=FetchType.EAGER, targetEntity=Authority.class, cascade=CascadeType.ALL)
	@JoinTable(name="tb_role_authority",joinColumns= {@JoinColumn(name="role_id")},inverseJoinColumns= {@JoinColumn(name="authority_id")}
)
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
