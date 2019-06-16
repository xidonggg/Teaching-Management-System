package com.ssh.modules.course.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "course")
public class Course {
	private String id;
	private String name;
	/**
	 * 编号，自定义
	 */
	private String number;
	private String describe;
	/**
	 * 多个教师用，隔开
	 */
	private String teachers;
	/**
	 * 主教师
	 */
	private String majorTeachers;
	/**
	 * 多个教室用,隔开，常用教室
	 */
	private String classrooms;
	/**
	 * 创建者，根据session获得
	 */
	private String establishPerson;
	/**
	 * 创建时间，根据本地时间获得
	 */
	private String establishTime;
	/**
	 * 安排的课节数
	 */
	private int teachTimes;
	private List<CourseArrange> courseArrange;
	
	public Course() {}
	public Course(String id, String name, String number, String describe, String teachers, String classrooms,
			String establishPerson, String establishTime, int teachTimes, List<CourseArrange> courseArrange) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.describe = describe;
		this.teachers = teachers;
		this.classrooms = classrooms;
		this.establishPerson = establishPerson;
		this.establishTime = establishTime;
		this.teachTimes = teachTimes;
		this.courseArrange = courseArrange;
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
	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "number", nullable = false, length = 32)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Column(name = "describe")
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Column(name = "teachers")
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	@Column(name = "classrooms")
	public String getClassrooms() {
		return classrooms;
	}
	public void setClassrooms(String classrooms) {
		this.classrooms = classrooms;
	}
	@Column(name = "establishPerson", nullable = false)
	public String getEstablishPerson() {
		return establishPerson;
	}
	public void setEstablishPerson(String establishPerson) {
		this.establishPerson = establishPerson;
	}
	@Column(name = "establishTime", nullable = false)
	public String getEstablishTime() {
		return establishTime;
	}
	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}
	@Column(name = "teachTimes")
	public int getTeachTimes() {
		return teachTimes;
	}
	public void setTeachTimes(int teachTimes) {
		this.teachTimes = teachTimes;
	}
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)//设置级联操作
	@JoinColumn(name = "tocourse") //指定多端的外键
	public List<CourseArrange> getCourseArrange() {
		return courseArrange;
	}
	public void setCourseArrange(List<CourseArrange> courseArrange) {
		this.courseArrange = courseArrange;
	}
	@Column(name = "majorTeachers")
	public String getMajorTeachers() {
		return majorTeachers;
	}
	public void setMajorTeachers(String majorTeachers) {
		this.majorTeachers = majorTeachers;
	}

}
