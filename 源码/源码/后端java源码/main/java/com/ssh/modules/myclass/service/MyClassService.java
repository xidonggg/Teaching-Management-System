package com.ssh.modules.myclass.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.course.dao.CourseDAO;
import com.ssh.modules.course.entity.CourseClass;
import com.ssh.modules.myclass.dao.MyClassDAO;
import com.ssh.modules.myclass.entity.MyClass;
import com.ssh.modules.myclass.model.RemoteTree;
import com.ssh.modules.myclass.remoteEntity.RemoteGetMyClass;
import com.ssh.modules.myclass.remoteEntity.RemoteSearchClass;

@Transactional
@Service
public class MyClassService {
	@Autowired
	private MyClassDAO myclassDAO;
	@Autowired
	private CourseDAO courseDAO;

	public void addMyClass(MyClass myclass) {
		myclassDAO.addMyClass(myclass);
	}

	public void updateMyClass(MyClass myclass) {
		myclassDAO.updateMyClass(myclass);
	}

	public void deleteMyClassById(String id) {
		myclassDAO.deleteMyClassById(id);
	}
	public List<MyClass>SearchClass(RemoteSearchClass searchclass){
		return myclassDAO.SearchClass(searchclass.getName(),searchclass.getStartyear(),searchclass.getSchool(),searchclass.getIsend());
	}

	@SuppressWarnings("unchecked")
	public List<MyClass> getMyClasses() {
		return myclassDAO.getMyClasses();
	}
	
	public List<MyClass>getAvaliableClass(){
		return myclassDAO.getAvaliableClass();
	}

	public MyClass getMyClassById(String id) throws Exception{
		return myclassDAO.getMyClassById(id);
	}
	/**
	 * 作为班主任的
	 * @param personName
	 * @return
	 */
	public List<RemoteTree> getMyclasses(String personName){
		List<MyClass> temp =  myclassDAO.getMyclasses(personName);
		return getClassTreeUtil(temp);
	}
	/**
	 * 作为教师的
	 * @param personName
	 * @return
	 */
	public List<RemoteTree> getTeachingClasses(String personName){
		List<String> mycourses = courseDAO.getMyCourse(personName);
		List<MyClass> temp = new ArrayList<MyClass>();
		for(int i = 0; i < mycourses.size(); i++) {
			List<CourseClass> temp2 = courseDAO.getCourseClassesByCourse(mycourses.get(i));
			for(int j = 0; j < temp2.size(); j++) {
				MyClass a = myclassDAO.getMyClassById(temp2.get(j).getClassId());
				temp.add(a);
			}
		}
		
		return getClassTreeUtil(temp);
	}
	
	public List<RemoteTree> getClassTree(){
		List<MyClass> myclasses = getMyClasses();
		return getClassTreeUtil(myclasses);
	}
	public List<RemoteTree> getClassTreeUtil(List<MyClass> myclasses){
		List<RemoteTree> ans = new ArrayList<RemoteTree>();
		for(int i = 0; i < myclasses.size(); i++) {
			MyClass temp = myclasses.get(i);
			boolean isExist = false;
			for(int j = 0; j < ans.size(); j++) {
				if(ans.get(j).getTitle().equals(temp.getSchool())) {
					isExist = true;
					//获得该校区下的所有子节点
					List<RemoteTree> year = ans.get(j).getChildren();
					boolean isExist2 = false;
					for(int k = 0; k < year.size(); k++) {
						if(year.get(k).getTitle().equals(temp.getStartyear())) {
							isExist2 = true;
							List<RemoteTree> classs = year.get(k).getChildren();
							classs.add(new RemoteTree(temp.getId(),temp.getName()+" "+temp.getIsend(),new ArrayList<RemoteTree>(),true,true));
							year.get(k).setChildren(classs);
							year.get(k).setIsLeaf(false);
						}
					}
					if(!isExist2) {
						RemoteTree ss = new RemoteTree(temp.getId(),temp.getName()+" "+temp.getIsend(),new ArrayList<RemoteTree>(),true,true);
						RemoteTree dd = new RemoteTree(temp.getStartyear(),temp.getStartyear(),new ArrayList<RemoteTree>(Arrays.asList(ss)),false,true);
						
						year.add(dd);
					}
					ans.get(j).setChildren(year);
					ans.get(j).setIsLeaf(false);
				}
			}
			if(!isExist) {//如果不存在该校区
				RemoteTree ss = new RemoteTree(temp.getId(),temp.getName()+" "+temp.getIsend(),new ArrayList<RemoteTree>(),true,true);
				RemoteTree dd = new RemoteTree(temp.getStartyear(),temp.getStartyear(),new ArrayList<RemoteTree>(Arrays.asList(ss)),false,true);
				RemoteTree rr = new RemoteTree(temp.getSchool(),temp.getSchool(),new ArrayList<RemoteTree>(Arrays.asList(dd)),false,true);
				
				ans.add(rr);
			}
		}
		System.out.println(ans);
		return ans;
	}
}
