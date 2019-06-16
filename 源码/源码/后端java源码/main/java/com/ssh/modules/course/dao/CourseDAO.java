package com.ssh.modules.course.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ssh.modules.course.entity.Course;
import com.ssh.modules.course.entity.CourseClass;

@Repository
public class CourseDAO {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addCourse(Course course) {
		this.getSession().save(course);
	}


	public void updateCourse(Course course) {
		this.getSession().update(course);
	}


	public void deleteCourseById(String id) {
		//Course course = (Course) this.getSession().createQuery("from Course where id=?").setParameter(0, id).uniqueResult();
		this.getSession().createQuery("delete CourseArrange where tocourse=?").setParameter(0, id).executeUpdate();
		this.getSession().createQuery("delete Course where id=?").setParameter(0, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCourses() {
		return this.getSession().createCriteria(Course.class).list();
	}
	@SuppressWarnings("unchecked")
	public List<CourseClass> getCourseClasses() {
		return this.getSession().createCriteria(CourseClass.class).list();
	}
	public void addCourseClassed(CourseClass courseclass) {
//		String sql = "insert into course_class_select(userName,passWord,email,address)values('"+userName+"','"+passWord+"','"+email+"','"+address+"')";
//        this.getSession().createSQLQuery(sql).executeUpdate();
		this.getSession().save(courseclass);
		//this.getSession().createQuery("update CourseClass set classId=? ,courseId=? where id=?").setParameter(0, classId).setParameter(1, courseId).setParameter(2, courseclass.getId()).executeUpdate();
	}
	public Course getCourseById(String id) {
		return (Course) this.getSession().createQuery("from Course where id=?").setParameter(0, id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<CourseClass> getCourseClassesByClassId(String classId){
		return this.getSession().createQuery("from CourseClass where classId=?").setParameter(0, classId).list();
	}
	public void deleteCourseClass(String id) {
		this.getSession().createQuery("delete CourseClass where id=?").setParameter(0, id).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getMyCourse(String pinyin) {
		return this.getSession().createQuery("select id from Course where majorTeachers like :pinyin").setString("pinyin", "%"+pinyin+"%").list();
	}
	@SuppressWarnings("unchecked")
	public List<Course> getMyCourseClass(String pinyin) {
		return this.getSession().createQuery("from Course where majorTeachers like :pinyin").setString("pinyin", "%"+pinyin+"%").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CourseClass> getCourseClassesByCourse(String courseId){
		return this.getSession().createQuery("from CourseClass where courseId=?").setParameter(0, courseId).list();
	}
}
