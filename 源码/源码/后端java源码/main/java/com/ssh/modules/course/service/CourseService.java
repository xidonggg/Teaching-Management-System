package com.ssh.modules.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.course.dao.CourseDAO;
import com.ssh.modules.course.entity.Course;
import com.ssh.modules.course.entity.CourseClass;
import com.ssh.modules.course.remoteEntity.RemoteCourseClass;
import com.ssh.modules.myclass.dao.MyClassDAO;

@Transactional
@Service
public class CourseService {
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private MyClassDAO myClassDAO;

	public void addCourse(Course course) {
		courseDAO.addCourse(course);
	}

	public Course getCourseById(String id) throws Exception{
		return courseDAO.getCourseById(id);
	}

	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}

	public void deleteCourseById(String id) {
		courseDAO.deleteCourseById(id);
	}

	public void deleteCourseClassSelect(String id) {
		courseDAO.deleteCourseClass(id);
	}
	public List<Course> getCourses() {
		List<Course> r = courseDAO.getCourses();
		for(int i = 0; i < r.size()-1; i++)
		{
			for(int j = i+1; j < r.size(); j++) {
				if(r.get(i).hashCode() == r.get(j).hashCode())
				{
					r.remove(r.get(j));
					j--;
					System.out.println(r.size());
				}
			}
		}
		return r;
	}
	public List<Course>getMyCourse(String pinyin){
		return courseDAO.getMyCourseClass(pinyin);
	}
	/**
	 * 根据班级id获得班级课程信息
	 * @param classId
	 * @return
	 */
	public List<RemoteCourseClass> getCourseClassesByClass(String classId){
		List<CourseClass> list = courseDAO.getCourseClassesByClassId(classId);
		return getCourseClassesUtils(list);
	}
	public List<RemoteCourseClass> getCourseClasses(){
		List<CourseClass> list = courseDAO.getCourseClasses();
		return getCourseClassesUtils(list);
	}
	public List<RemoteCourseClass> getCourseClassesUtils(List<CourseClass> list){
		List<RemoteCourseClass> ans = new ArrayList<RemoteCourseClass>();
		for(int i = 0; i < list.size(); i++) {
			CourseClass a = list.get(i);
			RemoteCourseClass c = new RemoteCourseClass();
			c.setId(a.getId());
			c.setCreateTime(a.getCreateTime());
			c.setCreatePerson(a.getCreatePerson());
			c.setCourse(courseDAO.getCourseById(a.getCourseId()));
			c.setMyclass(myClassDAO.getMyClassById(a.getClassId()));
			ans.add(c);
		}
		return ans;
	}

	public void setCourseClass(String classId,String courseIds,String createPerson, String createTime) {
		String[] courseIdss = courseIds.split(",");
		for(int i= 0; i < courseIdss.length; i++) {
			CourseClass courseClass = new CourseClass();
			courseClass.setCreatePerson(createPerson);
			courseClass.setCreateTime(createTime);
			courseClass.setClassId(classId);
			courseClass.setCourseId(courseIdss[i]);
			courseDAO.addCourseClassed(courseClass);
		}
	}
}
