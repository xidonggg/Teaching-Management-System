package com.ssh.Util.updownfile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.Util.updownfile.entity.Folder;


@Repository
public class FolderDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addFolder(Folder folder) {
		this.getSession().save(folder);
	}

	public void updateFolder(Folder folder) {
		this.getSession().update(folder);
	}

	public void deleteFolderById(String id) {
		this.getSession().createQuery("delete Folder where id=?").setParameter(0, id).executeUpdate();
	}
	public void deleteFolderByPath(String path) {
		this.getSession().createQuery("delete Folder where relativePath like :path").setString("path", path+"%").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Folder> getFolders() {
		return this.getSession().createCriteria(Folder.class).list();
	}

	public Folder getFolderById(String id) {
		return (Folder) this.getSession().createQuery("from Folder where id=?").setParameter(0, id).uniqueResult();
	}
	public Folder getFolderByPath(String path) {
		return (Folder) this.getSession().createQuery("from Folder where relativePath=?").setParameter(0, path).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Folder> getFoldersByPath(String rootpath){
		return this.getSession().createQuery("from Folder where relativePath like :path").setString("path", rootpath+"%").list();
	}
}
