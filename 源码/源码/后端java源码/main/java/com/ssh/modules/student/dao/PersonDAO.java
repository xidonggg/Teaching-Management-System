/**  
 * @Title: PersonDAO.java  
 * @Package com.ssh.dao  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.student.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.modules.student.entity.Person;

/**
 * @ClassName: PersonDAO
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:08:20
 */
@Repository
public class PersonDAO {

	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@SuppressWarnings("unchecked")
	public List<Person>getStudnetByClassId(String classId){
		return this.getSession().createQuery("from Person where classId=?").setParameter(0, classId).list();
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Person getPersonById(String id) {
		return (Person) this.getSession().createQuery("from Person where id=?").setParameter(0, id).uniqueResult();
	}
	public Person getPersonByPinyin(String pinyin) {
		return (Person) this.getSession().createQuery("from Person where pinyin=?").setParameter(0, pinyin).uniqueResult();
	}
	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void addPerson(Person person) {
		this.getSession().save(person);
	}

	/**
	 * 更新
	 * 
	 * @param person
	 */
	public void updatePerson(Person person) {
		this.getSession().update(person);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deletePersonById(String id) {
		this.getSession().createQuery("delete Person where id=?").setParameter(0, id).executeUpdate();
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		return this.getSession().createCriteria(Person.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getPersonByPinyinLike(String pinyin){
		return (List<Person>) this.getSession().createQuery("from Person where pinyin like :pinyin").setString("pinyin","%"+ pinyin+"%").list();
	}
	
	public void distributeClsss(String classId,String stuId) {
		this.getSession().createQuery("update Person set classId=? where id=?").setParameter(0, classId).setParameter(1, stuId).executeUpdate();
	}
}
