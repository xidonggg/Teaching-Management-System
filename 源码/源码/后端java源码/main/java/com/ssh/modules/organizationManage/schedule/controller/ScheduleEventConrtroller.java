package com.ssh.modules.organizationManage.schedule.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.Util.stringToList.StringToList;
import com.ssh.Util.timeFormat.TimeFormat;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.modules.finance.entity.FinanceTask;
import com.ssh.modules.organizationManage.schedule.entity.ScheduleEvent;
import com.ssh.modules.organizationManage.schedule.service.ScheduleEventService;
@RequestMapping(value = "/schedule")
@RestController
public class ScheduleEventConrtroller {
	@Autowired
	private ScheduleEventService scheduleService;
	/**
	 * 删除
	 * http://localhost:8081/springMVC-spring-hibernate/schedule/deleteScheduleEvent
	 */
	@PostMapping(value = "/deleteScheduleEvent")
	public CommonRsp<String> deleteScheduleEvent(String id){
		scheduleService.deleteScheduleEvent(id);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData("删除成功");
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 保存
	 * http://localhost:8081/springMVC-spring-hibernate/schedule/saveSechdule
	 */
	@PostMapping(value = "/saveSechdule")
	public CommonRsp<ScheduleEvent> saveSechdule(@RequestBody ScheduleEvent schedule) {
		scheduleService.addScheduleEvent(schedule);
	    CommonRsp<ScheduleEvent> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(schedule);
	    rsp.setType("data");
	    rsp.setResult(r); 
		return rsp;
	}
	/**
	 * 获得全部
	 * http://localhost:8081/springMVC-spring-hibernate/schedule/getSchedule
	 */
	@GetMapping(value="/getSchedule")
	public CommonRsp<ScheduleEvent> getSchedule(){
		List<ScheduleEvent> tasks = scheduleService.findAllScheduleEvent();
	    CommonRsp<ScheduleEvent> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(tasks);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
}
