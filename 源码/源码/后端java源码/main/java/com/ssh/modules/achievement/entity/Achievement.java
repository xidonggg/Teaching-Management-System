package com.ssh.modules.achievement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "achievement")
public class Achievement {
	private String id;
	/**
	 * 学生拼音
	 */
	private String stuPinyin;
	private String courseId;
	private String scId;
	private String score;
	private String createPerson;
	private String createTime;
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
	@Column(name = "stuPinyin")
	public String getStuPinyin() {
		return stuPinyin;
	}
	public void setStuPinyin(String stuPinyin) {
		this.stuPinyin = stuPinyin;
	}
	@Column(name = "courseId")
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	@Column(name = "score")
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Column(name = "createPerson")
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	@Column(name = "createTime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "scId")
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}

}
