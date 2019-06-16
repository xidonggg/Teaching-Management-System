package com.ssh.modules.finance.remoteEntity;

import java.util.List;

import com.ssh.modules.finance.entity.FinanceOrder;

public class RemoteFinance {
	private String id;
	private String name;
	/**
	 * 任务状态，已发布、未发布、已停止
	 */
	private String state;
	/**
	 * 已缴数目
	 */
	private int paidNumber;
	/**
	 * 未缴数目
	 */
	private int unpaidNumber;
	private String createPerson;
	private String createTime;
	/**
	 * 收费项名称
	 */
	private String chargeName;
	/**
	 * 金额
	 */
	private int amount;
	/**
	 * 备注
	 */
	private String remarks;
	
	private String stateChangeTime;
	private String stopTime;
	private List<RemoteFinanceOrder> financeOrders;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPaidNumber() {
		return paidNumber;
	}
	public void setPaidNumber(int paidNumber) {
		this.paidNumber = paidNumber;
	}
	public int getUnpaidNumber() {
		return unpaidNumber;
	}
	public void setUnpaidNumber(int unpaidNumber) {
		this.unpaidNumber = unpaidNumber;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStateChangeTime() {
		return stateChangeTime;
	}
	public void setStateChangeTime(String stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public List<RemoteFinanceOrder> getFinanceOrders() {
		return financeOrders;
	}
	public void setFinanceOrders(List<RemoteFinanceOrder> financeOrders) {
		this.financeOrders = financeOrders;
	}
	
}
