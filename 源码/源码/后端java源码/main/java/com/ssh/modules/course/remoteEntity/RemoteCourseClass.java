package com.ssh.modules.course.remoteEntity;

import com.ssh.modules.course.entity.Course;
import com.ssh.modules.myclass.entity.MyClass;

public class RemoteCourseClass {
	private String id;
	private MyClass myclass;
	private Course course;
	private String createPerson;
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public MyClass getMyclass() {
		return myclass;
	}
	public void setMyclass(MyClass myclass) {
		this.myclass = myclass;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
