/**  
 * @Title: PersonDAO.java  
 * @Package com.ssh.dao  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.organizationManage.organization.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.organizationManage.organization.entity.Org;

/**
 * @ClassName: PersonDAO
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:08:20
 */
@Repository
public class OrganizationDAO {

	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Org> getOrgs() {
		return this.getSession().createCriteria(Org.class).list();
	}

}
