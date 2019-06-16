package com.ssh.modules.myclass.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.myclass.entity.MyClass;


@Repository
public class MyClassDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addMyClass(MyClass myclass) {//,String roles
		this.getSession().save(myclass);
	}

	public void updateMyClass(MyClass myclass) {
		this.getSession().update(myclass);
	}

	public void deleteMyClassById(String id) {
		this.getSession().createQuery("delete MyClass where id=?").setParameter(0, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<MyClass> getMyClasses() {
		return this.getSession().createCriteria(MyClass.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<MyClass>getAvaliableClass(){
		return (List<MyClass>) this.getSession().createQuery("from MyClass where isend='N'").list();
	}
	public MyClass getMyClassById(String id) {
		return (MyClass) this.getSession().createQuery("from MyClass where id=?").setParameter(0, id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<MyClass> getMyclasses(String personName){
		return this.getSession().createQuery("from MyClass where directors like :path").setString("path","%"+ personName+"%").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MyClass>SearchClass(String name, String year, String school,String isend){
		boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
		String hql = "from MyClass where ";
		if(name != null && !name.equals("") && !name.equals("null")) {
			hql += "name like :name and ";
			flag1 = true;
		}
		if(year != null && !year.equals("") && !year.equals("null")) {
			hql += "startyear like:startyear and ";
			flag2 = true;
		}
		if(school != null && !school.equals("") && !school.equals("null")) {
			hql += "school like:school and ";
			flag3 = true;
		}
		if(isend != null && !isend.equals("") && !isend.equals("null")) {
			flag4 = true;
			if(isend.equals("是")) {
				hql += "isend='Y' ";
			}else if(isend.equals("否")) {
				hql += "isend='N' ";
			}
		}
		if(!flag1 && !flag2 && !flag3 && !flag4) {
			return getMyClasses();
		}else {
			Query query = this.getSession().createQuery(hql);
			if(flag1)
				query.setString("name", name);
			if(flag2)
				query.setString("startyear", year);
			if(flag3)
				query.setString("school", school);
			return query.list();
		}
		
	}
}
