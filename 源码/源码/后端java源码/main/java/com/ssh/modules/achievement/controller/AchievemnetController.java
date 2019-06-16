package com.ssh.modules.achievement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.modules.achievement.entity.Achievement;
import com.ssh.modules.achievement.service.AchievementService;

@RequestMapping(value = "/achievement")
@RestController
public class AchievemnetController {	
	@Autowired
	private AchievementService achievementService;
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/
	 * 请求方式：GET
	 */
	@PostMapping(value = "/getAchievementListBySCid")
	public CommonRsp<Achievement> getAchievementListBySCid(String id) {
		List<Achievement> list = achievementService.getAchievementByCsId(id);
	    CommonRsp<Achievement> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(list);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}}
