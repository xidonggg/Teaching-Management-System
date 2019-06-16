package com.ssh.modules.organizationManage.year.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.organizationManage.year.entity.SchoolYear;

@Transactional
@Repository
public class YearDAO {
	
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addYear(SchoolYear year) {
		this.getSession().save(year);
	}
	
	public void deleteYear(String id) {
		this.getSession().createQuery("delete SchoolYear where id=?").setParameter(0, id).executeUpdate();
	}
	
	public List<SchoolYear> findAllSchoolYear(){
		return this.getSession().createCriteria(SchoolYear.class).list();
	}

}
