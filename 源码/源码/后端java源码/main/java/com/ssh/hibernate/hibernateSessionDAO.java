package com.ssh.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;




public class hibernateSessionDAO implements Serializable {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Object getById(String id, @SuppressWarnings("rawtypes") Class type) {
		String type2 = type.getName();
		return (Object) this.getSession().createQuery("from ? where id=?").setParameter(0, type2).setParameter(1, id).uniqueResult();
	}

	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void add(Object person) {
		this.getSession().save(person);
	}

	/**
	 * 更新
	 * 
	 * @param person
	 */
	public void update(Object person) {
		this.getSession().update(person);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void remove(Object object) {
		this.getSession().delete(object);
	}
	public void removeById(String id, Class type) {
		String type2 = type.getName();
		this.getSession().createQuery("delete ? where id=?").setParameter(0, type2).setParameter(1, id).executeUpdate();
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> gets() {
		return this.getSession().createCriteria(Object.class).list();
	}
}
