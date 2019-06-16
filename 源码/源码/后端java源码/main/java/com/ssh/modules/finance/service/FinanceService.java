package com.ssh.modules.finance.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.Util.timeFormat.TimeFormat;
import com.ssh.exception.BusinessException;
import com.ssh.modules.finance.dao.FinanceDAO;
import com.ssh.modules.finance.entity.FinanceOrder;
import com.ssh.modules.finance.entity.FinanceTask;
import com.ssh.modules.finance.remoteEntity.RemoteFinance;
import com.ssh.modules.finance.remoteEntity.RemoteFinanceOrder;
import com.ssh.modules.student.dao.PersonDAO;
import com.ssh.modules.student.entity.Person;


@Transactional
@Service
public class FinanceService {

	@Autowired
	private FinanceDAO financeDAO;
	@Autowired
	private PersonDAO personDAO;
	public void addTask(FinanceTask financeTask, String[] stuId) {
		List<FinanceOrder> orderlist = new ArrayList<FinanceOrder>();
		String createTime = financeTask.getCreateTime();
		if(stuId.length == 0)
			throw new BusinessException("请选择缴费人");
		for(int i = 0; i < stuId.length; i++) {
			FinanceOrder order = new FinanceOrder(null, createTime, "未缴", stuId[i],
					createTime, financeTask.getId());
			orderlist.add(order);
		}
		financeTask.setFinanceOrders(orderlist);
		financeDAO.add(financeTask);
	}
	public List<FinanceTask> getTasks(){
		List<FinanceTask> r =financeDAO.getTasks();;
		for(int i = 0; i < r.size()-1; i++)
		{
			for(int j = i+1; j < r.size(); j++) {
				if(r.get(i).hashCode() == r.get(j).hashCode())
				{
					r.remove(r.get(j));
					j--;
					System.out.println(r.size());
				}
			}
		}
		return r;
	}
	public void deleteTask(String id) {
		FinanceTask task = financeDAO.getTaskById(id);
		
//		financeDAO.getTaskById(id);
		if(task.getState().equals("未发布")) {
			financeDAO.deleteTask(id);
		}else {
			throw new BusinessException("该任务无法删除");
		}
	}
	public void changestateStack(String id,String state) {
		String timenow = TimeFormat.stampToTime(new Date());
		if(state.equals("停止")) {
			financeDAO.saskfinished(id,timenow);
		}else {
			financeDAO.changeStateTask(id,state,timenow);
		}
	}
	public void changestateOrder(String id,String state) {
		if(state.equals("已缴")) {
			financeDAO.orderfinish(id);
		}
		String timenow = TimeFormat.stampToTime(new Date());
		financeDAO.changeStateOrder(id, state,timenow);
	}
	public List<RemoteFinanceOrder> getOrders(){
		List<FinanceOrder>list = financeDAO.getOrders();
		List<RemoteFinanceOrder> list2 = new ArrayList<RemoteFinanceOrder>();
		for(int i = 0; i < list.size(); i++) {
			RemoteFinanceOrder remoteorder = orderToRemote(list.get(i));
			remoteorder.setTaskName(financeDAO.getTaskById(remoteorder.getTaskId()).getName());
			list2.add(remoteorder);	
		}
		return list2;
	}
	public List<RemoteFinanceOrder> getOrdersByPerson(String pinyin){
		List<FinanceOrder>list = financeDAO.getOrdersByPerson(pinyin);
		List<RemoteFinanceOrder> list2 = new ArrayList<RemoteFinanceOrder>();
		for(int i = 0; i < list.size(); i++) {
			RemoteFinanceOrder remoteorder = orderToRemote(list.get(i));
			FinanceTask task = financeDAO.getTaskById(remoteorder.getTaskId());
			remoteorder.setTaskName(task.getName());
			remoteorder.setChargeName(task.getChargeName());
			remoteorder.setAmount(task.getAmount());
			list2.add(remoteorder);	
		}
		return list2;
	}
	public FinanceTask getTaskById(String id) {
		return financeDAO.getTaskById(id);
	}
	public RemoteFinance getRemoteTaskById(String id) {
		FinanceTask task = getTaskById(id);
		return financeToRemote(task);
	}
	public void financeStop(String id) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		FinanceTask task = financeDAO.getTaskById(id);
		Date date;
		try {
			date = formatter.parse(task.getStopTime());
			if(date.compareTo(new Date()) == -1) {
				String timenow = TimeFormat.stampToTime(new Date());
//				changestateStack(id,"停止");
				financeDAO.saskfinished(id,timenow);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	}
	private RemoteFinanceOrder orderToRemote(FinanceOrder order) {
		RemoteFinanceOrder remoteorder = new RemoteFinanceOrder();
		Person person = personDAO.getPersonByPinyin(order.getChargeAcount());
		remoteorder.setAccountPinyin(person.getPinyin());
		remoteorder.setAccountName(person.getName());
		remoteorder.setAccountPhone(person.getPhone());
		remoteorder.setChargeAcount(person.getId());
		remoteorder.setCreateTime(order.getCreateTime());
		remoteorder.setId(order.getId());
		remoteorder.setState(order.getState());
		remoteorder.setStateChangeTime(order.getStateChangeTime());
		remoteorder.setTaskId(order.getTaskId());
		return remoteorder;	
	}
	private RemoteFinance financeToRemote(FinanceTask task) {
		RemoteFinance remoteFinance = new RemoteFinance();
		remoteFinance.setAmount(task.getAmount());
		remoteFinance.setChargeName(task.getChargeName());
		remoteFinance.setCreatePerson(task.getCreatePerson());
		remoteFinance.setCreateTime(task.getCreateTime());
		remoteFinance.setId(task.getId());
		remoteFinance.setName(task.getName());
		remoteFinance.setPaidNumber(task.getPaidNumber());
		remoteFinance.setRemarks(task.getRemarks());
		remoteFinance.setState(task.getState());
		remoteFinance.setStateChangeTime(task.getStateChangeTime());
		remoteFinance.setStopTime(task.getStopTime());
		remoteFinance.setUnpaidNumber(task.getUnpaidNumber());
		List<RemoteFinanceOrder> list = new ArrayList<RemoteFinanceOrder>();
		for(int i = 0; i < task.getFinanceOrders().size(); i++) {
			list.add(orderToRemote(task.getFinanceOrders().get(i)));
		}
		remoteFinance.setFinanceOrders(list);
		return remoteFinance;
	}
}
