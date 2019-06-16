package com.ssh.login.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.login.bean.RedisUse;

@Repository
public class UserDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public RedisUse getRedisUse(String key) {
		return (RedisUse) this.getSession().createQuery("from RedisUse where key=?").setParameter(0, key).uniqueResult();
//		String hql = "select * from redisUse fp where fp.key = ?";
//		@SuppressWarnings("unchecked")
//		List<Object> list = getSession().createSQLQuery(hql)
//	        .setParameter(0, key)
//	        .list();
//		return (RedisUse) list.get(0);
	}
	
	public void setRedisValue(String key, String value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RedisUse ru = new RedisUse(null, key, value, sdf.format(new Date()));
		this.getSession().saveOrUpdate(ru);
		
	}
	public String getRedisValue(String key) {
		String hql = "select fp.value from redisUse fp where fp.key = ?";
		@SuppressWarnings("unchecked")
		List<Object> list = getSession().createSQLQuery(hql)
	        .setParameter(0, key)
	        .list();
		if(list.isEmpty())
			return null;
		return (String) list.get(0);
		
	}
	public void deleteRedis(String key) {
		String hql = "delete from redisUse fp where fp.key = ?";
		getSession().createSQLQuery(hql)
	        .setParameter(0, key)
	        .executeUpdate();
	}
	public void updateRedis(String key, String value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		RedisUse r = getRedisUse(key);
//		r.setCresteTime(sdf.format(new Date()));
		
		String hql = "update redisUse set cresteTime = ? where key = ?";
		getSession().createSQLQuery(hql)
        .setParameter(0, sdf.format(new Date()))
        .setParameter(1, key)
        .executeUpdate();
	}
}
