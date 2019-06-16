package com.ssh.modules.finance.remoteEntity;

import com.ssh.modules.finance.entity.FinanceOrder;

public class RemoteFinanceOrder {
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
	private String accountName;//账号名称
	private String accountPinyin;
	private String accountPhone;//账号手机号
	private String taskName;//所属任务名称
	private String chargeName;
	private int amount;
	private String taskstate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChargeAcount() {
		return chargeAcount;
	}
	public void setChargeAcount(String chargeAcount) {
		this.chargeAcount = chargeAcount;
	}
	public String getStateChangeTime() {
		return stateChangeTime;
	}
	public void setStateChangeTime(String stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPhone() {
		return accountPhone;
	}
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getAccountPinyin() {
		return accountPinyin;
	}
	public void setAccountPinyin(String accountPinyin) {
		this.accountPinyin = accountPinyin;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTaskstate() {
		return taskstate;
	}
	public void setTaskstate(String taskstate) {
		this.taskstate = taskstate;
	}
	
}
