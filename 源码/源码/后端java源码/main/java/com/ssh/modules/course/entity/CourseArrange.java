package com.ssh.modules.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "courseArrange")
public class CourseArrange {
	private String id;
	/**
	 * 上课日期，2019-5-17
	 */
	private String date;
	/**
	 * 作息时间段，12:00-13:40
	 */
	private String timeSlot;
	/**
	 * 上课教师, 严彬6/yanbin6,西瓜/xigua
	 */
	private String teachers;
	/**
	 * 上课地点，广知101
	 */
	private String classroom;
	
	public CourseArrange() {}
	public CourseArrange(String id, String date, String timeSlot, String teachers, String classroom) {
		super();
		this.id = id;
		this.date = date;
		this.timeSlot = timeSlot;
		this.teachers = teachers;
		this.classroom = classroom;
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
	@Column(name = "date", length = 255)
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(name = "timeSlot", length = 255)
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	@Column(name = "teachers", length = 255)
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	@Column(name = "classroom", length = 255)
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

}
