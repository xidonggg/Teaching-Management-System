/**  
 * @Title: PersonService.java  
 * @Package com.ssh.service  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.student.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.login.bean.UserLogin;
import com.ssh.login.dao.UserLoginDAO;
import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.modules.student.dao.PersonDAO;
import com.ssh.modules.student.entity.Person;

/**
 * @ClassName: PersonService
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:13:44
 */

@Transactional
@Service
public class PersonService {

	@Autowired
	public PersonDAO personDAO;
	@Autowired
	private UserLoginDAO userloginDAO;
	
	public List<Person> getStudentByClassId(String classId){
		return personDAO.getStudnetByClassId(classId);
	}
	public void distributeClsss(String classId,String stuId) {
		personDAO.distributeClsss(classId, stuId);
	}

	public Person getPersonByPinyin(String pinyin) {
		return personDAO.getPersonByPinyin(pinyin);
	}
	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void addPerson(Person person) {
		List<Person> staf = personDAO.getPersonByPinyinLike(person.getPinyin());
		if(staf != null && staf.size()>0) {
			Person st = staf.get(staf.size()-1);
			String pinyin  = st.getPinyin();
			Pattern pattern = Pattern.compile("[\\d]+");
			Matcher matcher = pattern.matcher(pinyin);
			if(matcher.find())
			{
				System.out.println(matcher.start());
				System.out.println(matcher.end());
				System.out.println(matcher.group());
				String num = matcher.group();
				int number = Integer.parseInt(num);
				number++;
				pinyin = pinyin.substring(0, matcher.start())+number;
				person.setPinyin("stu"+pinyin);
			}else {
				pinyin = person.getPinyin()+"1";
				person.setPinyin("stu"+pinyin);
			}

		}else {
			String pinyin = person.getPinyin()+"1";
			person.setPinyin("stu"+pinyin);
		}
		if(person.getPassword() == null || person.getPassword().equals(""))
			person.setPassword("123");
		if(person.getState() == null || person.getState().equals(""))
			person.setState("在读");
		if(person.getClassId() == null || person.getClassId().equals(""))
			person.setClassId("0000");
		personDAO.addPerson(person);
		createLoginUser(person);
	}
	private void createLoginUser(Person person) {
		UserLogin us = new UserLogin();
		us.setPassword(person.getPassword());
		us.setPinyin(person.getPinyin());
		us.setAuthorities("学生,查看选课权限");
		userloginDAO.addUserLogin(us);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Person getPersonById(String id) throws Exception{
//		throw new Exception();
		return personDAO.getPersonById(id);
	}

	/**
	 * 更新
	 * 
	 * @param person
	 */
	public void updatePerson(Person person) {
		personDAO.updatePerson(person);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deletePersonById(String id) {
		personDAO.deletePersonById(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Person> getPersons() {
		return personDAO.getPersons();
	}
}
