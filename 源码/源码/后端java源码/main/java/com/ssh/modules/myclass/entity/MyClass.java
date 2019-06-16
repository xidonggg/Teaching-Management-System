package com.ssh.modules.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "myclass")
public class MyClass {
	private String id;
	private String name;
	/**
	 * 指导教师，多个用,隔开
	 */
	private String directors;
	private String teachplace;
	/**
	 * 开班年份"2016"
	 */
	private String startyear;
	private String school;
	private boolean isend;
	private int totalTime;
	
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

	@Column(name = "teachplace", nullable = false, length = 32)
	public String getTeachplace() {
		return teachplace;
	}
	public void setTeachplace(String teachplace) {
		this.teachplace = teachplace;
	}
	@Column(name = "startyear", nullable = false, length = 4)
	public String getStartyear() {
		return startyear;
	}
	public void setStartyear(String startyear) {
		this.startyear = startyear;
	}
	@Column(name = "school", nullable = false, length = 32)
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@Type(type="yes_no")
	@Column(name = "isend", nullable = false)
	public boolean getIsend() {
		return isend;
	}
	public void setIsend(boolean isend) {
		this.isend = isend;
	}
	@Column(name = "totalTime", nullable = false)
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	@Column(name = "directors", nullable = false, length = 100)
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
}
