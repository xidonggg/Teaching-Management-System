package com.ssh.Util.updownfile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.Util.updownfile.entity.UploadFile;



@Repository
public class UploadFileDAO {
	
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addUploadFile(UploadFile uploadFile) {
		this.getSession().save(uploadFile);
	}

	public void updateUploadFile(UploadFile uploadFile) {
		this.getSession().update(uploadFile);
	}

	public void deleteUploadFileById(String id) {
		this.getSession().createQuery("delete UploadFile where id=?").setParameter(0, id).executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<UploadFile> getUploadFiles() {
		return this.getSession().createCriteria(UploadFile.class).list();
	}

	public UploadFile getUploadFileById(String id) {
		return (UploadFile) this.getSession().createQuery("from UploadFile where id=?").setParameter(0, id).uniqueResult();
	}
	
	public UploadFile getUploadFileByfileName(String fileName) {
		return (UploadFile) this.getSession().createQuery("from UploadFile where fileName=?").setParameter(0, fileName).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UploadFile> getUploadFilesByrelationID(String relationID) {
		return (List<UploadFile>) this.getSession().createQuery("from UploadFile where relationID=?").setParameter(0, relationID).list();
	} 
	@SuppressWarnings("unchecked")
	public List<UploadFile> getUploadFilesByrelativePath(String relativePath) {
		return (List<UploadFile>) this.getSession().createQuery("from UploadFile where relativePath=?").setParameter(0, relativePath).list();
	} 
}
