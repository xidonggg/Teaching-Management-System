package com.ssh.modules.enrollment.clue.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 招生计划实体类
 * @author 阿彬
 *
 */
@Entity
@Table(name = "enrollment_plan")
public class EnrollmentPlan {
	private String id;
	/**
	 * 招生计划名称
	 */
	private String name;
	/**
	 * 招生计划关联班级，二选一,班级可以是已经选课了的，也可以还未选课的
	 */
	private String myclasses;
	/**
	 * 招生计划关联课程，二选一
	 */
	private String courses;
	/**
	 * 招生开始时间"2029-1-2 12:00"
	 */
	private String startTime;
	/**
	 * 招生结束时间"2019-3-2 12:00"
	 */
	private String endTime;
	/**
	 * 创建人
	 */
	private String establishPerson;
	/**
	 * 创建时间
	 */
//	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "时间格式不正确")
	private String establishTime;
	/**
	 * 其他介绍信息
	 */
	private String describe;
	/**
	 * 联系人姓名
	 */
	private String communicateStaffname;
	/**
	 * 联系人电话
	 */
	private String communicateStaffPhone;
	/**
	 * 招生计划状态"招生中，未开始，已结束，终止"
	 */
	private String state;
	/**
	 * 计划人数
	 */
	private int planNumber;
	/**
	 * 目前已报名人数
	 */
	private int enrolmentNumber;
	
	
	
	
	public EnrollmentPlan() {
		super();
	}
	public EnrollmentPlan(String id, String name, String myclasses, String courses, String startTime, String endTime,
			String establishPerson,
			String establishTime,
			List<Clue> clues, String describe, String communicateStaffname, String communicateStaffPhone,
			String state) {
		super();
		this.id = id;
		this.name = name;
		this.myclasses = myclasses;
		this.courses = courses;
		this.startTime = startTime;
		this.endTime = endTime;
		this.establishPerson = establishPerson;
		this.establishTime = establishTime;
		this.describe = describe;
		this.communicateStaffname = communicateStaffname;
		this.communicateStaffPhone = communicateStaffPhone;
		this.state = state;
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
	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "myclasses", length = 32)
	public String getMyclasses() {
		return myclasses;
	}
	public void setMyclasses(String myclasses) {
		this.myclasses = myclasses;
	}
	@Column(name = "courses", length = 32)
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	@Column(name = "startTime", length = 32)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(name = "endTime", length = 32)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name = "establishPerson", length = 32)
	public String getEstablishPerson() {
		return establishPerson;
	}
	public void setEstablishPerson(String establishPerson) {
		this.establishPerson = establishPerson;
	}
	@Column(name = "establishTime", length = 32)
	public String getEstablishTime() {
		return establishTime;
	}
	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}
	@Column(name = "describe", length = 32)
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Column(name = "communicateStaffname", length = 32)
	public String getCommunicateStaffname() {
		return communicateStaffname;
	}
	public void setCommunicateStaffname(String communicateStaffname) {
		this.communicateStaffname = communicateStaffname;
	}
	@Column(name = "communicateStaffPhone", length = 32)
	public String getCommunicateStaffPhone() {
		return communicateStaffPhone;
	}
	public void setCommunicateStaffPhone(String communicateStaffPhone) {
		this.communicateStaffPhone = communicateStaffPhone;
	}
	@Column(name = "state", length = 32)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
//	public List<Clue> getClues() {
//		return clues;
//	}
//	public void setClues(List<Clue> clues) {
//		this.clues = clues;
//	}
//	public int getRateOfProcess() {
//		return rateOfProcess;
//	}
//	public void setRateOfProcess(int rateOfProcess) {
//		this.rateOfProcess = rateOfProcess;
//	}
	@Column(name = "planNumber")
	public int getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(int planNumber) {
		this.planNumber = planNumber;
	}
	@Column(name = "enrolmentNumber")
	public int getEnrolmentNumber() {
		return enrolmentNumber;
	}
	public void setEnrolmentNumber(int enrolmentNumber) {
		this.enrolmentNumber = enrolmentNumber;
	}
	
	

}
