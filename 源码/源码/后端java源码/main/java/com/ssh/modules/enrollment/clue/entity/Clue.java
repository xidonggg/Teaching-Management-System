package com.ssh.modules.enrollment.clue.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 线索实体类
 * @author 阿彬
 *
 */
@Entity
@Table(name = "enrollment_clue")
public class Clue {
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
	 * 线索状态“已录取，不录取，未录取”
	 */
	private String state;
	
	
	public Clue() {
		super();
	}

	public Clue(String id, String name, String phone, String email, String choiseclass, String choisecourse,
			String registrationTime, int intentionality, String source, List<CommunicationRecord> comminicationRecords,
			String toplan, String state) {
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
		this.communicationRecords = comminicationRecords;
		this.toplan = toplan;
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
	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "phone", nullable = false, length = 32)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "email", nullable = false, length = 32)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "choiseclass", nullable = false, length = 32)
	public String getChoiseclass() {
		return choiseclass;
	}
	public void setChoiseclass(String choiseclass) {
		this.choiseclass = choiseclass;
	}
	@Column(name = "choisecourse", nullable = false, length = 32)
	public String getChoisecourse() {
		return choisecourse;
	}
	public void setChoisecourse(String choisecourse) {
		this.choisecourse = choisecourse;
	}
	@Column(name = "registrationTime", nullable = false, length = 32)
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	@Column(name = "intentionality", nullable = false, length = 32)
	public int getIntentionality() {
		return intentionality;
	}
	public void setIntentionality(int intentionality) {
		this.intentionality = intentionality;
	}
	@Column(name = "source", nullable = false, length = 32)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)//设置级联操作
	@JoinColumn(name = "toclue") //指定多端的外键
	public List<CommunicationRecord> getCommunicationRecords() {
		return communicationRecords;
	}
	public void setCommunicationRecords(List<CommunicationRecord> communicationRecords) {
		this.communicationRecords = communicationRecords;
	}
	@Column(name = "toplan", nullable = false, length = 32)
	public String getToplan() {
		return toplan;
	}
	public void setToplan(String toplan) {
		this.toplan = toplan;
	}
	@Column(name = "state", length = 32)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	
	

}
