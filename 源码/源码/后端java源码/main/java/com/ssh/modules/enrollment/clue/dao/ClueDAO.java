package com.ssh.modules.enrollment.clue.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.enrollment.clue.entity.Clue;
import com.ssh.modules.enrollment.clue.entity.CommunicationRecord;


@Repository
public class ClueDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void addClue(Clue clue) {
		this.getSession().save(clue);
	}


	public void updateClue(Clue clue) {
		this.getSession().update(clue);
	}


	public void deleteClueById(String id) {
		//Course course = (Course) this.getSession().createQuery("from Course where id=?").setParameter(0, id).uniqueResult();
		this.getSession().createQuery("delete CommunicationRecord where tocommunication=?").setParameter(0, id).executeUpdate();
		this.getSession().createQuery("delete Clue where id=?").setParameter(0, id).executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<Clue> getClues() {
		return this.getSession().createCriteria(Clue.class).list();
	}

	public Clue getClueById(String id) {
		return (Clue) this.getSession().createQuery("from Clue where id=?").setParameter(0, id).uniqueResult();
	}
	
	public int getNumberByPlanId(String id) {
		String hql = "select count(*) from Clue where toplan=?";
		int count = ((Number)this.getSession().createQuery(hql).setParameter(0, id).uniqueResult()).intValue();
		return count;
	}
	public void changeState(String id,String state) {
		this.getSession().createQuery("update Clue set state=? where id=?").setParameter(0, state).setParameter(1, id).executeUpdate();
	}
	public List<Clue> getCluesByPlanId(String id){
		String hql = "from Clue where toplan=?";
		//@SuppressWarnings("unchecked")
		//List<Clue> clues = null;
		List<Clue> clues = this.getSession().createQuery(hql).setParameter(0, id).list();
		//System.out.println(obj);
		return clues;
	}
}
