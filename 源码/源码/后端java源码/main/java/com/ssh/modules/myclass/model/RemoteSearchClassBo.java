package com.ssh.modules.myclass.model;

public class RemoteSearchClassBo {
	private String isend;
	private String school;
	private String startyear;
	private String name;
	
	public RemoteSearchClassBo(String isend, String school, String startyear, String name) {
		super();
		this.isend = isend;
		this.school = school;
		this.startyear = startyear;
		this.name = name;
	}
	public String getIsend() {
		return isend;
	}
	public void setIsend(String isend) {
		this.isend = isend;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getStartyear() {
		return startyear;
	}
	public void setStartyear(String startyear) {
		this.startyear = startyear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
