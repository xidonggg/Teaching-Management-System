package com.ssh.modules.enrollment.clue.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.hibernate.hibernateSessionDAO;
import com.ssh.modules.enrollment.clue.entity.Clue;
import com.ssh.modules.enrollment.clue.entity.CommunicationRecord;

@Repository
public class CommunicationRecordDAO extends hibernateSessionDAO{
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void addCommunicationRecord(CommunicationRecord communicationRecord) {
		super.add(communicationRecord);
	}


	public void updateRecord(CommunicationRecord communicationRecord) {
		this.getSession().update(communicationRecord);
	}


	public void deleteRecordById(String id) {
		//Course course = (Course) this.getSession().createQuery("from Course where id=?").setParameter(0, id).uniqueResult();
		this.getSession().createQuery("delete CommunicationRecord where id=?").setParameter(0, id).executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<Clue> getRecords() {
		return this.getSession().createCriteria(CommunicationRecord.class).list();
	}

	public Clue getRecordById(String id) {
		return (Clue) this.getSession().createQuery("from CommunicationRecord where id=?").setParameter(0, id).uniqueResult();
	}
	public List<CommunicationRecord> getRecordByClueId(String id) {
		String hql = "from CommunicationRecord where toclue=?";
		List<CommunicationRecord> communicationRecord = this.getSession().createQuery(hql).setParameter(0, id).list();
		return communicationRecord;
	}
}
