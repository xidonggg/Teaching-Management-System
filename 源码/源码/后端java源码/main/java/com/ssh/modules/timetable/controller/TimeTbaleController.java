package com.ssh.modules.timetable.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.modules.course.entity.Course;
import com.ssh.modules.timetable.remoteEntity.RemoteTimeTable;
import com.ssh.modules.timetable.service.TimeTableService;

@RequestMapping(value = "/timetable")
@RestController
public class TimeTbaleController {
	@Autowired
	private TimeTableService timeTableService;
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/timetable/getTimeTable
	 * @param classId
	 * @return
	 */
	@PostMapping(value = "/getTimeTable")
	public CommonRsp<RemoteTimeTable> getTimeTable(String classId) {
		List<RemoteTimeTable> ans = timeTableService.getTimeTable(classId);
	    CommonRsp<RemoteTimeTable> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
}
