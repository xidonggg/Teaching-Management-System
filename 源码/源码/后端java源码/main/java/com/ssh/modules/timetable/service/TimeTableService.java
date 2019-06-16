package com.ssh.modules.timetable.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.course.dao.CourseDAO;
import com.ssh.modules.course.entity.Course;
import com.ssh.modules.course.entity.CourseArrange;
import com.ssh.modules.course.entity.CourseClass;
import com.ssh.modules.myclass.dao.MyClassDAO;
import com.ssh.modules.timetable.remoteEntity.RemoteTimeTable;

@Transactional
@Service
public class TimeTableService {
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private MyClassDAO myClassDAO;
	

	
	public List<RemoteTimeTable> getTimeTable(String classId){
		List<CourseClass> list = courseDAO.getCourseClassesByClassId(classId);
		List<RemoteTimeTable> ans = new ArrayList<RemoteTimeTable>();
		for(int i = 0; i < list.size(); i++) {
			CourseClass cc = list.get(i);
			Course cs = courseDAO.getCourseById(cc.getCourseId());
			for(int j = 0; j < cs.getCourseArrange().size(); j++) {
				CourseArrange ca = cs.getCourseArrange().get(j);
				
				RemoteTimeTable a = new RemoteTimeTable();
				String[] timeslot = ca.getTimeSlot().split("-");
				String startTime = ca.getDate()+" "+ timeslot[0];
				String endTime = ca.getDate()+" "+timeslot[1];
				a.setStart(startTime);
				a.setEnd(endTime);
				a.setTitle("课程名称:"+cs.getName()+";授课教师:"+ca.getTeachers()+";地点:"+ca.getClassroom()+";时间:"+startTime+"-"+endTime);
				Map<Integer,String> mm = new HashMap<Integer,String>();
				mm.put(0, "red");
				mm.put(1, "blue");
				mm.put(2, "yellow");
				int random = (int)Math.random()*10 % 3;
				a.setColor(mm.get(random));
				ans.add(a);
			}
		}
		return ans;
	}
}
