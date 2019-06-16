package com.ssh.modules.staffmanage.staff.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.ssh.modules.organizationManage.role.entity.Role;
@Entity
@Table(name = "staff")
public class Staff {
	
	private String id;
	/**
	 * 名称包括拼音，如“严彬2/yanbin2”
	 */
	private String name;
	private String sex;
	private String phone;
	/**
	 * 所属部门ids，用“,”隔开
	 * */
	private String departments;
	/**
	 * 入职日期
	 * */
	private String hiredate;
	private String email;
	private String idCard;
	private String address;
	private String pinyin;
	/**
	 * 用户角色，用新的表tb_staff_role存储
	 * */
	private List<Role> roles;
	
	/**
	 * 教师登陆密码
	 */
	private String password;
	
	private String state;
	private String picUrl;
	
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
	@Column(name = "name", nullable = false, length = 32,unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "sex", nullable = false, length = 32)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "phone", nullable = false, length = 32)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "departments", nullable = false, length = 32)
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	@Column(name = "hiredate", nullable = false, length = 32)
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	@Column(name = "email", nullable = false, length = 32)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch=FetchType.EAGER, targetEntity=Role.class)// cascade=CascadeType.
	@JoinTable(name="tb_staff_role",joinColumns= {@JoinColumn(name="staff_id")},inverseJoinColumns= {@JoinColumn(name="role_id")})
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "idCard")
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "pinyin")
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "picUrl")
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
	
	

}
