/**  
 * @Title: PersonService.java  
 * @Package com.ssh.service  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.organizationManage.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.organizationManage.schedule.dao.ScheduleEventDAO;
import com.ssh.modules.organizationManage.schedule.entity.ScheduleEvent;

/**
 * @ClassName: PersonService
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:13:44
 */

@Transactional
@Service
public class ScheduleEventService {

	@Autowired
	public ScheduleEventDAO scheduleEventDAO;

	/**
	 * 添加
	 * 
	 * @param person
	 */
	public void addScheduleEvent(ScheduleEvent scheduleEvent) {
		scheduleEventDAO.addScheduleEvent(scheduleEvent);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteScheduleEvent(String id) {
		scheduleEventDAO.deleteScheduleEvent(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<ScheduleEvent> findAllScheduleEvent() {
		return scheduleEventDAO.findAllScheduleEvent();
	}
}
