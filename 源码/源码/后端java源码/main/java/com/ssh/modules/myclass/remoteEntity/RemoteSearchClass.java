package com.ssh.modules.myclass.remoteEntity;

public class RemoteSearchClass {
	/**
	 * "是"，"否"
	 */
	private String isend;
	/**
	 * 模糊查询
	 */
	private String school;
	/**
	 * 2018
	 */
	private String startyear;
	/**
	 * 模糊查询
	 */
	private String name;
	
	public RemoteSearchClass() {
		super();
	}
	public RemoteSearchClass(String isend, String school, String startyear, String name) {
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
