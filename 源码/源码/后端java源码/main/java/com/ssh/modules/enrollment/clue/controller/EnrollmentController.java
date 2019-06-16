package com.ssh.modules.enrollment.clue.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.Util.timeFormat.TimeFormat;
import com.ssh.Util.tokenToUserName.TokenToUser;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.exception.BusinessException;
import com.ssh.modules.enrollment.clue.entity.Clue;
import com.ssh.modules.enrollment.clue.entity.CommunicationRecord;
import com.ssh.modules.enrollment.clue.entity.EnrollmentPlan;
import com.ssh.modules.enrollment.clue.remoteEntity.RemoteClue;
import com.ssh.modules.enrollment.clue.remoteEntity.RemoteEnrollment;
import com.ssh.modules.enrollment.clue.service.EnrollmentService;

@RequestMapping(value = "/enrollment")
@RestController
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentPlanService;
	@Autowired
	private TokenToUser tokenToUser;
	/**
	 * 新建招生计划
	 * http://localhost:8081/springMVC-spring-hibernate/enrollment/saveEnrollment
	 */
	@PostMapping(value = "/saveEnrollment")
	public CommonRsp<EnrollmentPlan> saveEnrollment(@RequestBody EnrollmentPlan plan, @RequestHeader("token")String token) {
		plan.setEstablishPerson(tokenToUser.tokenToLoginUser(token).getPinyin());
		plan.setEstablishTime(TimeFormat.stampToTime(new Date()));
		plan.setState("未发布");
		enrollmentPlanService.addEnrollemntPlan(plan);
	    CommonRsp<EnrollmentPlan> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(plan);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 修改招生计划状态
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/updateEnrollment?_method=PUT
	 * 请求方式：POST
	 */
	@PutMapping(value = "/updateEnrollment")
	public CommonRsp<EnrollmentPlan> updateEnrollment(String id, String state) {
		EnrollmentPlan plan = (EnrollmentPlan) enrollmentPlanService.changeEnrollmentPlanState(id, state);
	    CommonRsp<EnrollmentPlan> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(plan);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/get/297ee3fc6804ec26016804ec46f80000
	 * 请求方式：GET
	 */
	@GetMapping(value = "/get/{id}")
	public CommonRsp<RemoteEnrollment> get(@PathVariable("id") String id) {
		RemoteEnrollment enrollmentPlan;
			enrollmentPlan = enrollmentPlanService.getEnrollmentPlan(id);
	    CommonRsp<RemoteEnrollment> rsp = new CommonRsp<RemoteEnrollment>();
	    Result r = new Result();
	    rsp.setData(enrollmentPlan);
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/listEnrollmentPlans
	 * 请求方式：GET
	 */
	@GetMapping(value = "/listEnrollmentPlans")
	public CommonRsp<EnrollmentPlan> listEnrollmentPlans(Map<String, Object> map) {
		List<EnrollmentPlan> ans = enrollmentPlanService.getEnrollmentPlans();
	    CommonRsp<EnrollmentPlan> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/listEnrollmentingPlans
	 * 请求方式：GET
	 */
	@GetMapping(value = "/listEnrollmentingPlans")
	public CommonRsp<RemoteEnrollment> listEnrollmentingPlans(Map<String, Object> map) {
		List<RemoteEnrollment> ans = enrollmentPlanService.getEnrollmentingPlans();
	    CommonRsp<RemoteEnrollment> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/listClues
	 * 请求方式：GET
	 */
	@GetMapping(value = "/listClues")
	public CommonRsp<RemoteClue> listClues(Map<String, Object> map) {
		List<RemoteClue> ans = enrollmentPlanService.getAllClues();
	    CommonRsp<RemoteClue> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 新建线索
	 * http://localhost:8081/springMVC-spring-hibernate/enrollment/saveClue
	 */
	@PostMapping(value = "/saveClue")
	public CommonRsp<Clue> saveClue(@RequestBody Clue clue) {
		
		clue.setRegistrationTime(TimeFormat.stampToTime(new Date()));
		clue.setState("未录取");
		enrollmentPlanService.addClue(clue);
	    CommonRsp<Clue> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(clue);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 修改线索状态
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/updateClue?_method=PUT
	 * 请求方式：POST
	 */
	@PutMapping(value = "/updateClue")
	public CommonRsp<Clue> updateClue(String id, String state) {
		enrollmentPlanService.changeClueState(id, state);
	    CommonRsp<Clue> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(null);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 修改线索状态
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/enrollment/updateClueAll?_method=PUT
	 * 请求方式：POST
	 */
	@PutMapping(value = "/updateClueAll")
	public CommonRsp<Clue> updateClueAll(@RequestBody Clue clue) {
		enrollmentPlanService.updateClue(clue);
	    CommonRsp<Clue> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(null);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/enrollment/getCluesByPlan/297ee3fc6804ec26016804ec46f80000
	 * 根据计划id查找线索
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getCluesByPlan/{id}")
	public CommonRsp<Clue> getClue(@PathVariable("id") String id) {
		List<Clue>	clues = enrollmentPlanService.getCluesByPlanId(id);
	    CommonRsp<Clue> rsp = new CommonRsp<Clue>();
	    Result r = new Result();
	    rsp.setList(clues);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/enrollment/getClueById/297ee3fc6804ec26016804ec46f80000
	 * 根据线索id查找线索
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getClueById/{id}")
	public CommonRsp<RemoteClue> getClueById(@PathVariable("id") String id) {
		RemoteClue clues = enrollmentPlanService.getClue(id);
	    CommonRsp<RemoteClue> rsp = new CommonRsp<RemoteClue>();
	    Result r = new Result();
	    rsp.setData(clues);
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/enrollment/getRecordsByClueId/297ee3fc6804ec26016804ec46f80000
	 * 根据计划线索查记录
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getRecordsByClueId/{id}")
	public CommonRsp<CommunicationRecord> getRecordById(@PathVariable("id") String id) {
		List<CommunicationRecord> communicationRecord = enrollmentPlanService.getCommunicationByClueId(id);
	    CommonRsp<CommunicationRecord> rsp = new CommonRsp<CommunicationRecord>();
	    Result r = new Result();
	    rsp.setList(communicationRecord);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 新建沟通记录
	 * http://localhost:8081/springMVC-spring-hibernate/enrollment/saveRecord
	 */
	@PostMapping(value = "/saveRecord")
	public CommonRsp<CommunicationRecord> saveRecord(@RequestBody CommunicationRecord communicationRecord) {
		enrollmentPlanService.addCommunication(communicationRecord);
	    CommonRsp<CommunicationRecord> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(communicationRecord);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
}
