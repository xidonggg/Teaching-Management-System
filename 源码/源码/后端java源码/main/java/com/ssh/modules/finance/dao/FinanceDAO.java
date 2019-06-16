package com.ssh.modules.finance.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.course.entity.Course;
import com.ssh.modules.finance.entity.FinanceOrder;
import com.ssh.modules.finance.entity.FinanceTask;
@Repository
public class FinanceDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void add(Object object) {
		getSession().save(object);
	}
	public void update(Object object) {
		getSession().update(object);
	}
	public void deleteTask(String id) {
		this.getSession().createQuery("delete FinanceOrder where taskId=?").setParameter(0, id).executeUpdate();
		this.getSession().createQuery("delete FinanceTask where id=?").setParameter(0, id).executeUpdate();
	}
	public FinanceTask getTaskById(String id) {
		FinanceTask e = (FinanceTask) this.getSession().createQuery("from FinanceTask where id=?").setParameter(0, id).uniqueResult();
//		System.out.println(e);
		return e;
//		String sql = "se"
	}
	public FinanceOrder getOrderById(String id) {
		return (FinanceOrder) this.getSession().createQuery("from FinanceOrder where id=?").setParameter(0, id).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<FinanceTask> getTasks(){
		return this.getSession().createCriteria(FinanceTask.class).list();
	}
	@SuppressWarnings("unchecked")
	public List<FinanceOrder> getOrders(){
		return this.getSession().createCriteria(FinanceOrder.class).list();
	}
	public void changeStateTask(String id,String state,String newdata) {
		this.getSession().createQuery("update FinanceTask set state=? ,stateChangeTime=? where id=?").setParameter(0, state).setParameter(1, newdata).setParameter(2, id).executeUpdate();
	}
	public void changeStateOrder(String id,String state,String newdate) {
		this.getSession().createQuery("update FinanceOrder set state=? ,stateChangeTime=? where id=?").setParameter(0, state).setParameter(1, newdate).setParameter(2, id).executeUpdate();
	}
	public void saskfinished(String id,String newdate) {
		this.getSession().createQuery("update FinanceTask set state='停止' ,stateChangeTime=? where id=?").setParameter(0, newdate).setParameter(1, id).executeUpdate();
//		this.getSession().createQuery("update FinanceOrder set state='失效' ,stateChangeTime=? where taskId=?").setParameter(0, newdate).setParameter(1, id).executeUpdate();
	}
	public void orderfinish(String id) {
		FinanceOrder a = (FinanceOrder)this.getSession().createQuery("from FinanceOrder where id=?").setParameter(0, id).uniqueResult();
		FinanceTask e = (FinanceTask) this.getSession().createQuery("from FinanceTask where id=?").setParameter(0, a.getTaskId()).uniqueResult();
		e.setUnpaidNumber(e.getUnpaidNumber()-1);
		e.setPaidNumber(e.getPaidNumber()+1);
	}
	@SuppressWarnings("unchecked")
	public List<FinanceOrder> getOrdersByPerson(String pinyin){
		return this.getSession().createQuery("from FinanceOrder where chargeAcount=?").setParameter(0, pinyin).list();
	}
}
