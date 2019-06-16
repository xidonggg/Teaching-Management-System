package com.ssh.modules.enrollment.clue.remoteEntity;

import java.util.Collections;
import java.util.List;

import com.ssh.modules.enrollment.clue.dao.EnrollmentPlanDAO;
import com.ssh.modules.enrollment.clue.entity.Clue;
import com.ssh.modules.enrollment.clue.entity.CommunicationRecord;

public class RemoteClue {
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	private String phone;
	private String email;
	/**
	 * 选择的班级
	 */
	private String choiseclass;
	/**
	 * 选择的课程
	 */
	private String choisecourse;
	/**
	 * 报名时间
	 */
	private String registrationTime;
	/**
	 * 意向度
	 */
	private int intentionality;
	/**
	 * 来源"同学推荐、教职工推荐、微信推送、广告"
	 */
	private String source;
	
	private List<CommunicationRecord> communicationRecords;
	/**
	 * 指定EnrollmentPlan的手动外键
	 */
	private String toplan;
	/**
	 * 招生计划名称
	 */
	private String planName;
	/**
	 * 线索状态“已录取，不录取，未录取”
	 */
	private String state;
	public static RemoteClue ClueToRemoteClue(Clue clue,String planName) {
		RemoteClue remoteClue = new RemoteClue(clue.getId(),clue.getName(),clue.getPhone(),clue.getEmail(),clue.getChoiseclass(),clue.getChoisecourse(),
				clue.getRegistrationTime(),clue.getIntentionality(),clue.getSource(),clue.getCommunicationRecords(),clue.getToplan(),
				planName,clue.getState());
		
		return remoteClue;
	}
	public RemoteClue(String id, String name, String phone, String email, String choiseclass, String choisecourse,
			String registrationTime, int intentionality, String source, List<CommunicationRecord> communicationRecords,
			String toplan, String planName, String state) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.choiseclass = choiseclass;
		this.choisecourse = choisecourse;
		this.registrationTime = registrationTime;
		this.intentionality = intentionality;
		this.source = source;
		this.communicationRecords = communicationRecords;
		this.toplan = toplan;
		this.planName = planName;
		this.state = state;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChoiseclass() {
		return choiseclass;
	}
	public void setChoiseclass(String choiseclass) {
		this.choiseclass = choiseclass;
	}
	public String getChoisecourse() {
		return choisecourse;
	}
	public void setChoisecourse(String choisecourse) {
		this.choisecourse = choisecourse;
	}
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	public int getIntentionality() {
		return intentionality;
	}
	public void setIntentionality(int intentionality) {
		this.intentionality = intentionality;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<CommunicationRecord> getCommunicationRecords() {
		return communicationRecords;
	}
	public void setCommunicationRecords(List<CommunicationRecord> communicationRecords) {
		this.communicationRecords = communicationRecords;
	}
	public String getToplan() {
		return toplan;
	}
	public void setToplan(String toplan) {
		this.toplan = toplan;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
