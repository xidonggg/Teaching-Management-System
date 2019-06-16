package com.ssh.modules.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "finance_order")
public class FinanceOrder {
	private String id;
	private String createTime;
	/**
	 * 状态，未缴、已缴、失效（订单）
	 */
	private String state;
	/**
	 * 关联收费账号
	 */
	private String chargeAcount;
	private String stateChangeTime;
	/**
	 * 关联任务id
	 */
	private String taskId;
	
	public FinanceOrder() {
		super();
	}
	public FinanceOrder(String id, String createTime, String state, String chargeAcount,
			 String stateChangeTime, String taskId) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.state = state;
		this.chargeAcount = chargeAcount;
		this.stateChangeTime = stateChangeTime;
		this.taskId = taskId;
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
	@Column(name = "createTime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "chargeAcount")
	public String getChargeAcount() {
		return chargeAcount;
	}
	public void setChargeAcount(String chargeAcount) {
		this.chargeAcount = chargeAcount;
	}

	@Column(name = "stateChangeTime")
	public String getStateChangeTime() {
		return stateChangeTime;
	}
	public void setStateChangeTime(String stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}
	@Column(name = "taskId")
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
