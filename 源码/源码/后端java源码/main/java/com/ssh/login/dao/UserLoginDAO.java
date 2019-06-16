package com.ssh.login.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.login.bean.UserLogin;


@Repository
public class UserLoginDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addUserLogin(UserLogin userLogin) {
		this.getSession().save(userLogin);
	}

	public void updateUserLogin(UserLogin userLogin) {
		this.getSession().update(userLogin);
	}

	public void deleteUserLoginById(String id) {
		this.getSession().createQuery("delete UserLogin where id=?").setParameter(0, id).executeUpdate();
	}
	public void deleteUserLoginByPinyin(String pinyin) {
		this.getSession().createQuery("delete UserLogin where pinyin=?").setParameter(0, pinyin).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<UserLogin> getUserLogins() {
		return this.getSession().createCriteria(UserLogin.class).list();
	}

	public UserLogin getUserLoginById(String id) {
		return (UserLogin) this.getSession().createQuery("from UserLogin where id=?").setParameter(0, id).uniqueResult();
	}
	
	public UserLogin getUserLoginBypinyin(String pinyin) {
		return (UserLogin) this.getSession().createQuery("from UserLogin where pinyin=?").setParameter(0, pinyin).uniqueResult();
	}


}
