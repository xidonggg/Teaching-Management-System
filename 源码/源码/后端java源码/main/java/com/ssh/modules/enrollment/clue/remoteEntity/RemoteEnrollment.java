package com.ssh.modules.enrollment.clue.remoteEntity;

import java.util.List;

import com.ssh.modules.course.entity.Course;
import com.ssh.modules.enrollment.clue.entity.Clue;
import com.ssh.modules.myclass.entity.MyClass;

public class RemoteEnrollment {
	private String id;
	/**
	 * 招生计划名称
	 */
	private String name;
	/**
	 * 招生计划关联班级，二选一,班级可以是已经选课了的，也可以还未选课的
	 */
	private String myclasses;
	private String myclassesname;
	private List<MyClass> myclassesclass;
	/**
	 * 招生计划关联课程，二选一
	 */
	private String courses;
	private String coursesname;
	private List<Course> coursesclass;
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
	/**
	 * xiansuo
	 */
	private List<Clue> clues;
	
	
	public RemoteEnrollment() {
		super();
	}

	public RemoteEnrollment(String id, String name, String myclasses, List<MyClass> myclassesclass, String courses,
			List<Course> coursesclass, String startTime, String endTime, String establishPerson, String establishTime,
			String describe, String communicateStaffname, String communicateStaffPhone, String state, int planNumber,
			int enrolmentNumber, List<Clue> clues) {
		super();
		this.id = id;
		this.name = name;
		this.myclasses = myclasses;
		this.myclassesclass = myclassesclass;
		this.courses = courses;
		this.coursesclass = coursesclass;
		this.startTime = startTime;
		this.endTime = endTime;
		this.establishPerson = establishPerson;
		this.establishTime = establishTime;
		this.describe = describe;
		this.communicateStaffname = communicateStaffname;
		this.communicateStaffPhone = communicateStaffPhone;
		this.state = state;
		this.planNumber = planNumber;
		this.enrolmentNumber = enrolmentNumber;
		this.clues = clues;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEstablishPerson() {
		return establishPerson;
	}
	public void setEstablishPerson(String establishPerson) {
		this.establishPerson = establishPerson;
	}
	public String getEstablishTime() {
		return establishTime;
	}
	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getCommunicateStaffname() {
		return communicateStaffname;
	}
	public void setCommunicateStaffname(String communicateStaffname) {
		this.communicateStaffname = communicateStaffname;
	}
	public String getCommunicateStaffPhone() {
		return communicateStaffPhone;
	}
	public void setCommunicateStaffPhone(String communicateStaffPhone) {
		this.communicateStaffPhone = communicateStaffPhone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(int planNumber) {
		this.planNumber = planNumber;
	}
	public int getEnrolmentNumber() {
		return enrolmentNumber;
	}
	public void setEnrolmentNumber(int enrolmentNumber) {
		this.enrolmentNumber = enrolmentNumber;
	}
	public List<Clue> getClues() {
		return clues;
	}
	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public String getMyclasses() {
		return myclasses;
	}

	public void setMyclasses(String myclasses) {
		this.myclasses = myclasses;
	}

	public List<MyClass> getMyclassesclass() {
		return myclassesclass;
	}

	public void setMyclassesclass(List<MyClass> myclassesclass) {
		this.myclassesclass = myclassesclass;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public List<Course> getCoursesclass() {
		return coursesclass;
	}

	public void setCoursesclass(List<Course> coursesclass) {
		this.coursesclass = coursesclass;
	}

	public String getMyclassesname() {
		return myclassesname;
	}

	public void setMyclassesname(String myclassesname) {
		this.myclassesname = myclassesname;
	}

	public String getCoursesname() {
		return coursesname;
	}

	public void setCoursesname(String coursesname) {
		this.coursesname = coursesname;
	}
	
	
}
