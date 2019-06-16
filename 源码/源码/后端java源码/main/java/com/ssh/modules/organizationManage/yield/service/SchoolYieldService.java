package com.ssh.modules.organizationManage.yield.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.organizationManage.yield.dao.SchoolYieldDAO;
import com.ssh.modules.organizationManage.yield.entity.SchoolYield;

@Transactional
@Service
public class SchoolYieldService {
	@Autowired
	private SchoolYieldDAO schoolYieldDAO;
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<SchoolYield> getSchoolYield() {
		return schoolYieldDAO.getSchoolYields();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteSchoolYieldById(String id) {
		schoolYieldDAO.deleteSchoolYieldById(id);
	}
	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void addSchoolYield(SchoolYield schoolYield) {
		schoolYieldDAO.addSchoolYoeld(schoolYield);
	}

}
