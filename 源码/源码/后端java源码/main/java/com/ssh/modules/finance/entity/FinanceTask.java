package com.ssh.modules.finance.entity;

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
@Entity
@Table(name = "finance_task")
public class FinanceTask {
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
	private List<FinanceOrder> financeOrders;
	
	public FinanceTask() {
		super();
	}
	public FinanceTask(String id, String name, String state, int paidNumber, int unpaidNumber,
			String createPerson, String createTime, String chargeName, int amount, String remarks,
			String stateChangeTime, String stopTime) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.paidNumber = paidNumber;
		this.unpaidNumber = unpaidNumber;
		this.createPerson = createPerson;
		this.createTime = createTime;
		this.chargeName = chargeName;
		this.amount = amount;
		this.remarks = remarks;
		this.stateChangeTime = stateChangeTime;
		this.stopTime = stopTime;
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
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "paidNumber")
	public int getPaidNumber() {
		return paidNumber;
	}
	public void setPaidNumber(int paidNumber) {
		this.paidNumber = paidNumber;
	}
	@Column(name = "unpaidNumber")
	public int getUnpaidNumber() {
		return unpaidNumber;
	}
	public void setUnpaidNumber(int unpaidNumber) {
		this.unpaidNumber = unpaidNumber;
	}
	@Column(name = "createPerson")
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	@Column(name = "createTime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "chargeName")
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	@Column(name = "amount")
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "stateChangeTime")
	public String getStateChangeTime() {
		return stateChangeTime;
	}
	public void setStateChangeTime(String stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}
	@Column(name = "stopTime")
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)//设置级联操作
	@JoinColumn(name = "taskId") //指定多端的外键
	public List<FinanceOrder> getFinanceOrders() {
		return financeOrders;
	}
	public void setFinanceOrders(List<FinanceOrder> financeOrders) {
		this.financeOrders = financeOrders;
	}

}
