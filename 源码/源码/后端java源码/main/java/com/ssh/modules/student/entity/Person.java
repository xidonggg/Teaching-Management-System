/**  
 * @Title: Person.java  
 * @Package com.ssh.entity  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.student.entity;

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
@Table(name = "person")
public class Person {

	private String id; // 主键id
	private String name; // 姓名
	private String password;
	private String idCard; // 身份证号
	private String sex;//性别
	private String birthday;
	private String email;
	private String phone; // 手机号
	private String address; // 地址
	private String pinyin;//拼音，作为用户名
	private String description;//其他信息
	private String classId;//班级id
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

	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "idCard", nullable = false, length = 32)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "phone", nullable = false, length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", length = 32)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "sex", length = 32)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "birthday", length = 32)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Column(name = "email", length = 32)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", idCard=" + idCard + ", phone=" + phone + ", address="
				+ address + "sex=" + sex + "birthday=" +birthday + "email" + email+ "]";
	}
	@Column(name = "pinyin")
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "classId")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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