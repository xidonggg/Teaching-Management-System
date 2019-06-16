package com.ssh.modules.achievement.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.achievement.entity.Achievement;


@Repository
public class AchievementDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void addAchievement(Achievement course) {
		this.getSession().save(course);
	}


	public void updateAchievement(Achievement course) {
		this.getSession().update(course);
	}
	public List<Achievement> getAchievementByScId(String scid) {
		return (List<Achievement>) this.getSession().createQuery("from Achievement where scid=?").setParameter(0, scid).list();
	}

	@SuppressWarnings("unchecked")
	public List<Achievement> getAchievements() {
		return this.getSession().createCriteria(Achievement.class).list();
	}
}
