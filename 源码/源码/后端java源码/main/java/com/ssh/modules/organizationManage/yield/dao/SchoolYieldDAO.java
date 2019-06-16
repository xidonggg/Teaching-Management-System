package com.ssh.modules.organizationManage.yield.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.organizationManage.yield.entity.SchoolYield;
@Repository
public class SchoolYieldDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void addSchoolYoeld(SchoolYield schoolyield) {
		this.getSession().save(schoolyield);
	}
	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteSchoolYieldById(String id) {
		this.getSession().createQuery("delete SchoolYield where id=?").setParameter(0, id).executeUpdate();
	}
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SchoolYield> getSchoolYields() {
		return this.getSession().createCriteria(SchoolYield.class).list();
	}
}
