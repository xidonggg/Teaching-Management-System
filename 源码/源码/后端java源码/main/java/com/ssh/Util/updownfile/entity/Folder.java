package com.ssh.Util.updownfile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "updownload_folder")
public class Folder {

	private int id;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 父文件夹id
	 */
	private int parentId;
	/**
	 * 相对路径
	 */
	private String relativePath;
	/**
	 * 创建时间
	 */
	private String eatablishTime;
	/**
	 * 创建人
	 */
	private String eatablishPerson;
	/**
	 * 是否删除
	 */
	private boolean isdelete;
	
	public Folder() {
		super();
	}
	public Folder(int id, String name, int parentId, String relativePath, String eatablishTime, String eatablishPerson,
			boolean isdelete) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.relativePath = relativePath;
		this.eatablishTime = eatablishTime;
		this.eatablishPerson = eatablishPerson;
		this.isdelete = isdelete;
	}
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@SequenceGenerator(name = "seq", sequenceName = "id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "parentId")
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Column(name = "relativePath",unique = true)
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	@Column(name = "eatablishTime")
	public String getEatablishTime() {
		return eatablishTime;
	}
	public void setEatablishTime(String eatablishTime) {
		this.eatablishTime = eatablishTime;
	}
	@Column(name = "eatablishPerson")
	public String getEatablishPerson() {
		return eatablishPerson;
	}
	public void setEatablishPerson(String eatablishPerson) {
		this.eatablishPerson = eatablishPerson;
	}
	@Type(type="yes_no")
	@Column(name = "isdelete")
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
}
