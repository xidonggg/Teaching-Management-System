package com.ssh.modules.staffmanage.staff.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.modules.student.entity.Person;


@Repository
public class StaffDAO {
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
	public void addStaff(Staff staff) {//,String roles
		this.getSession().save(staff);
	}

	/**
	 * 更新
	 * 
	 * @param person
	 */
	public void updateStaff(Staff staff) {
		this.getSession().update(staff);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteStaffById(String id) {
		this.getSession().createQuery("delete Staff where id=?").setParameter(0, id).executeUpdate();
	}
	public Staff getStaffByPinyin1(String pinyin) {
		return (Staff) this.getSession().createQuery("from Staff where pinyin=?").setParameter(0, pinyin).uniqueResult();
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> getStaffs() {
		return this.getSession().createCriteria(Staff.class).list();
	}
//	@SuppressWarnings("unchecked")
//	public List<Staff> getStaffs(int start, int end) {
//		return this.getSession().createCriteria(Staff.class).list().subList(start, end);
//	}
	@SuppressWarnings("unchecked")
	public List<Staff> getStaffs(int start, int length) {
		return this.getSession().createSQLQuery("select * from staff limit ? offset ?").addEntity(Staff.class).setParameter(0, length).setParameter(1, start).list();
	}
	public int getStaffsNum() {
		BigInteger b = (BigInteger) this.getSession().createSQLQuery("select count(1) from Staff").uniqueResult();
		return b.intValue();
	}
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Staff getStaffById(String id) {
		return (Staff) this.getSession().createQuery("from Staff where id=?").setParameter(0, id).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Staff> getStaffByPinyin(String pinyin) {
		return (List<Staff>) this.getSession().createQuery("from Staff where pinyin like :pinyin").setString("pinyin","%"+ pinyin+"%").list();
	}
	@SuppressWarnings("unchecked")
	public List<Staff> getStaffByDepartmentId(String departmentId) {
		return (List<Staff>) this.getSession().createQuery("from Staff where departments like :departments").setString("departments","%"+ departmentId+"%").list();
	}
	public List<String> getStaffNames(){
        String hql = "select o.pinyin,o.name from Staff o";
        @SuppressWarnings("unchecked")
		List<Object> aa = getSession()
                .createQuery(hql)
                .list();
        List<String> bb = new ArrayList<String>();
        for(int i = 0; i < aa.size(); i++) {
        	String json = JSONObject.valueToString(aa.get(i));
        	bb.add(json);
        	System.out.println(json);
        }
        return bb;
	}
	public void stateChange(String id,String state) {
		this.getSession().createQuery("update Staff set state=? where id=?").setParameter(0, state).setParameter(1, id).executeUpdate();
	}
}
