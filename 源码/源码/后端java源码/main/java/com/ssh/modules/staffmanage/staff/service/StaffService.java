package com.ssh.modules.staffmanage.staff.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.exception.BusinessException;
import com.ssh.login.bean.UserLogin;
import com.ssh.login.dao.UserLoginDAO;
import com.ssh.modules.organizationManage.role.entity.Authority;
import com.ssh.modules.organizationManage.role.entity.Role;
import com.ssh.modules.staffmanage.staff.dao.StaffDAO;
import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.modules.student.entity.Person;

@Transactional
@Service
public class StaffService {
	
	@Autowired
	private StaffDAO staffDAO;
	@Autowired
	private UserLoginDAO userloginDAO;
	public void stateChange(String id,String pinyin,String state) {
		staffDAO.stateChange(id,state);
		if(state.equals("离职")) {
			userloginDAO.deleteUserLoginByPinyin(pinyin);
		}
	}
	public Staff getStaffByPinyin(String pinyin) {
		return staffDAO.getStaffByPinyin1(pinyin);
	}
	public List<Staff> getStaffByDepartmentId(String departmentId){
		return staffDAO.getStaffByDepartmentId(departmentId);
	}
	
	public List<Staff> getPageStaffByDepartmentId(String departmentId, int start, int length){
		int num = staffDAO.getStaffByDepartmentId(departmentId).size();
		if(start >= num || start < 0) {
			return Collections.emptyList();
		}else if(start + length >= num) {
			staffDAO.getStaffByDepartmentId(departmentId).subList(start, num-start);
			return staffDAO.getStaffs(start, num-start);
		}else {
			return staffDAO.getStaffByDepartmentId(departmentId).subList(start, length);
		}
	}
	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void addStaff(Staff staff) {
//		UserLogin ul = new UserLogin();
		List<Staff> staf = staffDAO.getStaffByPinyin(staff.getPinyin());
		if(staf != null && staf.size()>0) {
			Staff st = staf.get(staf.size()-1);
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
				staff.setPinyin(pinyin);
			}else {
				pinyin = staff.getPinyin()+"1";
				staff.setPinyin(pinyin);
			}

		}else {
			String pinyin = staff.getPinyin()+"1";
			staff.setPinyin(pinyin);
		}
		if(staff.getPassword() == null || staff.getPassword().equals(""))
			staff.setPassword("123");
		if(staff.getState() == null || staff.getState().equals(""))
			staff.setState("在职");
		staffDAO.addStaff(staff);
		addLoginUser(staff);
	}
	private void addLoginUser(Staff staff) {
		UserLogin user = new UserLogin();
		user.setPinyin(staff.getPinyin());
		user.setPassword(staff.getPassword());
		List<String>au = new ArrayList<String>();
		String authorities = "";
		List<Role> roles = staff.getRoles();
		for(int i = 0; i < roles.size(); i++) {
			List<Authority> authority = roles.get(i).getAuthorities();
			for(int j = 0; j < authority.size(); j++) {
				String name = authority.get(j).getName();
				Boolean flag = true;
				for(int k = 0; k < au.size(); k++) {
					if(au.get(k).equals(name)) {
						flag = false;
					}
				}
				if(flag) {
					au.add(name);
				}
			}
		}
		for(int i = 0; i < au.size(); i++) {
			authorities += au.get(i)+",";
		}
		authorities = authorities.substring(0, authorities.length()-1);
//		authorities = au.toString();
		user.setAuthorities(authorities.substring(1, authorities.length()-1));	
		userloginDAO.addUserLogin(user);
	}

	/**
	 * 更新
	 * 
	 * @param person
	 */
	public void updateStaff(Staff staff) {
		staffDAO.updateStaff(staff);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteStaffById(String id) {
		staffDAO.deleteStaffById(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> getStaffs() {
		return staffDAO.getStaffs();
		
	}
	public int getStaffNum() {
		return staffDAO.getStaffsNum();
	}
	public List<Staff>getStaffs(int start,int length){
		int num = staffDAO.getStaffsNum();
		if(start >= num || start < 0) {
			return Collections.emptyList();
		}else if(start + length >= num) {
			return staffDAO.getStaffs(start, num-start);
		}else {
			return staffDAO.getStaffs(start, length);
		}
	}
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Staff getStaffById(String id) throws Exception{
		return staffDAO.getStaffById(id);
	}
	
	public List<String> getStaffNames() {
		List<String> names = staffDAO.getStaffNames();
		List<String> keys = new ArrayList<String>();
//		for(int i = 0; i < names.size(); i++) {
//			int t = names.get(i).indexOf("/");
//			if(t == -1)
//				throw new BusinessException("staff数据有问题");
//			String tt = names.get(i).substring(t);
//			keys.add(tt);
//		}
		//插入法排序
		keys = names;
		for(int i = 1; i < keys.size(); i++) {
			for(int j = 0; j < i; j++) {
				if(comparatorIsaBigger(keys.get(j),keys.get(i))) {
					String a = keys.get(i);
					String b = names.get(i);
					for(int k = i-1; k > j-1; k--) {
						keys.set(k+1, keys.get(k));
						names.set(k+1, names.get(k));
					}
					keys.set(j, a);
					names.set(j, b);
				}
			}

		}
		return names;
	}
	
	private boolean comparatorIsaBigger(String a, String b) {
		//例如a="yanbin5"，b="xigua"
		if(a == null || a.length() == 0)
			return false;
		else if(b == null || b.length() == 0)
			return true;
		int i = 0;
		while(true) {
			if(a.charAt(i) > b.charAt(i))
				return true;
			else if(a.charAt(i) < b.charAt(i))
				return false;
			else {
				if(a.length() == i+1)//如果a结束了
					return false;
				else if(b.length() == i+1)//如果b结束了
					return true;
				else {
					i++;
				}
			}
		}
	}
}
