package com.ssh.modules.enrollment.clue.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.Util.timeFormat.TimeFormat;
import com.ssh.modules.enrollment.clue.entity.EnrollmentPlan;

@Repository
public class EnrollmentPlanDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void changeState(String id, String state) {
		this.getSession().createQuery("update EnrollmentPlan set state=? where id=?").setParameter(0, state).setParameter(1, id).executeUpdate();
	}
	public void addClue(EnrollmentPlan enrollmentPlan) {
		this.getSession().save(enrollmentPlan);
	}


	public void updateEnrollmentPlan(EnrollmentPlan enrollmentPlan) {
		this.getSession().update(enrollmentPlan);
	}


	public void deleteEnrollmentPlanById(String id) {
		//Course course = (Course) this.getSession().createQuery("from Course where id=?").setParameter(0, id).uniqueResult();
		this.getSession().createQuery("delete Clue where toclue=?").setParameter(0, id).executeUpdate();
		this.getSession().createQuery("delete EnrollmentPlan where id=?").setParameter(0, id).executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<EnrollmentPlan> getEnrollmentPlans() {
//		List<EnrollmentPlan> l = this.sessionFactory.openSession().createCriteria(EnrollmentPlan.class).list();
//		this.sessionFactory.close();
//		return l;
		return this.getSession().createCriteria(EnrollmentPlan.class).list();
	}

	public EnrollmentPlan getEnrollmentPlanById(String id) {
		EnrollmentPlan e = (EnrollmentPlan) this.getSession().createQuery("from EnrollmentPlan where id=?").setParameter(0, id).uniqueResult();
		return e;
	}
	public List<EnrollmentPlan> getEnrollmentingPlan() {
		String date = TimeFormat.stampToTime(new Date());
		System.out.println(date);
		List<EnrollmentPlan> e = this.getSession().createQuery("from EnrollmentPlan where to_date(startTime,'yyyy-mm-dd hh24:mi:ss') < to_date(?,'yyyy-mm-dd hh24:mi:ss')"
				+ " and to_date(endTime,'yyyy-mm-dd hh24:mi:ss') > to_date(?,'yyyy-mm-dd hh24:mi:ss')" 
				+" and state='发布' ").setParameter(0, date).setParameter(1, date).list();
		return e;
	}
}
