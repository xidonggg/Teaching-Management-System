package com.ssh.modules.enrollment.clue.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 沟通记录实体类
 * @author 阿彬
 *
 */
@Entity
@Table(name = "enrollment_comminication_record")
public class CommunicationRecord {
	private String id;
	/**
	 * 联系方式“电话、邮箱、面谈、视频”
	 */
	private String style;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 联系人
	 */
	private String contentPsersonName;
	/**
	 * 沟通时间
	 */
	private String communicateTime;
	
	public CommunicationRecord() {
		super();
	}
	public CommunicationRecord(String id, String style, String content, String contentPsersonName,
			String communicateTime) {
		super();
		this.id = id;
		this.style = style;
		this.content = content;
		this.contentPsersonName = contentPsersonName;
		this.communicateTime = communicateTime;
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
	@Column(name = "style", length = 32)
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	@Column(name = "content",  length = 200)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "contentPsersonName", length = 32)
	public String getContentPsersonName() {
		return contentPsersonName;
	}
	public void setContentPsersonName(String contentPsersonName) {
		this.contentPsersonName = contentPsersonName;
	}
	@Column(name = "communicateTime", length = 32)
	public String getCommunicateTime() {
		return communicateTime;
	}
	public void setCommunicateTime(String communicateTime) {
		this.communicateTime = communicateTime;
	}

}
