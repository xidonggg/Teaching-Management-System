package com.ssh.modules.organizationManage.role.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.organizationManage.role.entity.Authority;
import com.ssh.modules.organizationManage.role.entity.Role;

@Repository
public class RoleDAOImpl{

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
//	@Override
	public List<Authority> getAllAuthorities() {
		
		try {
			return this.getSession().createCriteria(Authority.class).list();
		}catch(RuntimeException e) {
			System.out.println("鑾峰彇Authority鍑虹幇閿欒");
			e.printStackTrace();
		}		
		return null;
	}
//	@Override
	public List<Role> getAllRoles() {
		try {
			return this.getSession().createCriteria(Role.class).list();
			//String hql = ""
		}catch(RuntimeException e) {
			System.out.println("鑾峰彇Role鍑虹幇閿欒");
			e.printStackTrace();
		}		
		return null;
	}
//	@Override
	public void addRole(Role role) {
		this.getSession().save(role);
		
	}
//	@Override
	public void addAuthority(Authority authority) {
		this.getSession().save(authority);
		
	}
//	@Override
	public void deleteRole(String id) {
//		String sql = "delete from tb_role_authority where role_id=?";
//		this.getSession().createQuery(sql).setParameter(0, id).executeUpdate();
		String sql2 = "delete from Role where id=?";
		this.getSession().createQuery(sql2).setParameter(0, id).executeUpdate();
		
	}
//	@Override
	public void updateRole(Role role) {
		this.getSession().update(role);
		
	}
//	@Override
	public Role getRole(String id) {
		return (Role) this.getSession().createQuery("from Role where id=?").setParameter(0, id).uniqueResult();
		
	}

}
