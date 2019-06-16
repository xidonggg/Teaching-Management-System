package com.ssh.modules.organizationManage.schedule.dao;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.organizationManage.schedule.entity.ScheduleEvent;


@Repository
public class ScheduleEventDAO {
	
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addScheduleEvent(ScheduleEvent scheduleEvent) {
		this.getSession().save(scheduleEvent);
	}
	
	public void deleteScheduleEvent(String id) {
		this.getSession().createQuery("delete ScheduleEvent where id=?").setParameter(0, id).executeUpdate();
	}
	
	public List<ScheduleEvent> findAllScheduleEvent(){
		return this.getSession().createCriteria(ScheduleEvent.class).list();
	}

}
