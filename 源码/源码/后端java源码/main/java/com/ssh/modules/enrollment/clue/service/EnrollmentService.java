package com.ssh.modules.enrollment.clue.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.Util.stringToList.StringToList;
import com.ssh.modules.course.dao.CourseDAO;
import com.ssh.modules.course.entity.Course;
import com.ssh.modules.enrollment.clue.dao.ClueDAO;
import com.ssh.modules.enrollment.clue.dao.CommunicationRecordDAO;
import com.ssh.modules.enrollment.clue.dao.EnrollmentPlanDAO;
import com.ssh.modules.enrollment.clue.entity.Clue;
import com.ssh.modules.enrollment.clue.entity.CommunicationRecord;
import com.ssh.modules.enrollment.clue.entity.EnrollmentPlan;
import com.ssh.modules.enrollment.clue.remoteEntity.RemoteClue;
import com.ssh.modules.enrollment.clue.remoteEntity.RemoteEnrollment;
import com.ssh.modules.myclass.dao.MyClassDAO;
import com.ssh.modules.myclass.entity.MyClass;

@Transactional
@Service
public class EnrollmentService {
	@Autowired
	private ClueDAO clueDAO;
	@Autowired
	private CommunicationRecordDAO communicationRecordDAO;
	@Autowired
	private EnrollmentPlanDAO enrollmentPlanDAO;
	@Autowired
	private MyClassDAO myclassDAO;
	@Autowired
	private CourseDAO courseDAO;
	/**
	 * 新建招生计划.
	 */
	public void addEnrollemntPlan(EnrollmentPlan enrollmentPlan) {
		enrollmentPlanDAO.addClue(enrollmentPlan);
	}
	/**
	 * 修改招生计划状态.
	 */
	public EnrollmentPlan changeEnrollmentPlanState(String id, String state) {
		enrollmentPlanDAO.changeState(id, state);
		return enrollmentPlanDAO.getEnrollmentPlanById(id);
	}
	/**
	 * 获取全部招生计划以及招生进度.
	 */
	public List<EnrollmentPlan> getEnrollmentPlans() {
		List<EnrollmentPlan> noratePlans = enrollmentPlanDAO.getEnrollmentPlans();
		for(EnrollmentPlan plan: noratePlans) {
			plan.setEnrolmentNumber(getrateEnrollmentPlan(plan.getId()));
		}	
		return noratePlans;
	}
	/**
	 * 获取在招生中的招生计划以及招生进度.
	 */
	public List<RemoteEnrollment> getEnrollmentingPlans() {
		List<EnrollmentPlan> noratePlans = enrollmentPlanDAO.getEnrollmentingPlan();
		List<RemoteEnrollment> ans = new ArrayList<RemoteEnrollment>();
		for(EnrollmentPlan plan: noratePlans) {
			plan.setEnrolmentNumber(getrateEnrollmentPlan(plan.getId()));
			ans.add(enrollmentToRemote(plan));
		}	
		return ans;
	}
	/**
	 * 根据id获取已报名人数.
	 * @param id
	 * @return
	 */
	public int getrateEnrollmentPlan(String id) {
		return clueDAO.getNumberByPlanId(id);
	}
	/**
	 * 获取招生计划详细信息.
	 */
	public RemoteEnrollment getEnrollmentPlan(String id) {
		EnrollmentPlan temp = enrollmentPlanDAO.getEnrollmentPlanById(id);
		RemoteEnrollment re = enrollmentToRemote(temp);
		return re;
	}
	/**
	 * 新建线索。
	 */
	public void addClue(Clue clue){
		RemoteEnrollment enrollmentPlan = getEnrollmentPlan(clue.getToplan());
		String choiseCourse = enrollmentPlan.getCoursesname();
		String choiseClass = enrollmentPlan.getMyclassesname();
		clue.setChoiseclass(choiseClass);
		clue.setChoisecourse(choiseCourse);
		clueDAO.addClue(clue);
	}
	/**
	 * 修改线索状态。
	 */
	public void changeClueState(String id,String state) {
		clueDAO.changeState(id, state);
	}
	/**
	 * 根据招生计划获取线索.
	 */
	public List<Clue> getCluesByPlanId(String id) {
		return clueDAO.getCluesByPlanId(id);
	}
	/**
	 * 根据id获取线索信息.
	 */
	public RemoteClue getClue(String id) {
		Clue temp = clueDAO.getClueById(id);
		if(temp != null) {
			EnrollmentPlan e = enrollmentPlanDAO.getEnrollmentPlanById(temp.getToplan());
			RemoteClue r = RemoteClue.ClueToRemoteClue(temp, e.getName());
			return r;
		}
		return null;
	}
	/**
	 * 新建沟通记录
	 */
	public void addCommunication(CommunicationRecord cd) {
		communicationRecordDAO.add(cd);
	}
	public void updateClue(Clue clue) {
		clueDAO.updateClue(clue);
	}
	/**
	 * 根据线索查看沟通记录
	 * @return 
	 */
	public List<CommunicationRecord> getCommunicationByClueId(String id) {
		return communicationRecordDAO.getRecordByClueId(id);
	}
	/**
	 * 查看全部线索.
	 * @return
	 */
	public List<RemoteClue>getAllClues(){
		List<Clue> temps = clueDAO.getClues();
		List<RemoteClue> rc = new ArrayList<RemoteClue>();
		for(Clue temp:temps) {
			EnrollmentPlan e = enrollmentPlanDAO.getEnrollmentPlanById(temp.getToplan());
			RemoteClue r = RemoteClue.ClueToRemoteClue(temp, e.getName());
			rc.add(r);
		}
		return rc;
	}
	
	
	//-----------Enrollment切换到Remote-------------
	public RemoteEnrollment enrollmentToRemote(EnrollmentPlan enrollment) {
		//转换班级
		List<MyClass> myclass = new ArrayList<MyClass>();
		StringBuffer myclassname = new StringBuffer();
		if(enrollment.getMyclasses() != null && !enrollment.getMyclasses().equals("")) {
			String[] myclassIds = StringToList.stringToList(enrollment.getMyclasses());
			for(int i = 0; i < myclassIds.length; i++) {
				MyClass mc = myclassDAO.getMyClassById(myclassIds[i]);
				myclass.add(mc);
				myclassname.append(mc.getName()+",");
			}
		}

		//转换课程
		List<Course> mycourse = new ArrayList<Course>();
		StringBuffer mycoursename = new StringBuffer();
		if(enrollment.getCourses() != null && !enrollment.getCourses().equals("")) {
			String[] mycourseIds = StringToList.stringToList(enrollment.getCourses());			
			for(int i = 0; i < mycourseIds.length; i++) {
				Course mc = courseDAO.getCourseById(mycourseIds[i]);
				mycourse.add(mc);
				mycoursename.append(mc.getName()+",");
			}
		}
		
		
		List<Clue> myclue = clueDAO.getCluesByPlanId(enrollment.getId());
		RemoteEnrollment re = new RemoteEnrollment(enrollment.getId(),enrollment.getName(),
				enrollment.getMyclasses(),myclass,enrollment.getCourses(),mycourse,enrollment.getStartTime(),
				enrollment.getEndTime(),enrollment.getEstablishPerson(),enrollment.getEstablishTime(),
				enrollment.getDescribe(),enrollment.getCommunicateStaffname(),enrollment.getCommunicateStaffPhone()
				,enrollment.getState(),enrollment.getPlanNumber()
				,getrateEnrollmentPlan(enrollment.getId()),myclue);
		re.setMyclassesname(myclassname.substring(0, myclassname.length()>0? myclassname.length()-1:0));
		re.setCoursesname(mycoursename.substring(0, mycoursename.length()>0? mycoursename.length()-1:0));
		return re;
		
	}
}
