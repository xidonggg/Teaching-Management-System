package com.ssh.modules.organizationManage.year.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.modules.organizationManage.year.dao.YearDAO;
import com.ssh.modules.organizationManage.year.entity.SchoolYear;
import com.ssh.vo.Msg;

@SessionAttributes(value = "username")
@RequestMapping(value = "/year")
@RestController
public class YearController {

	@Autowired
	private YearDAO yearDAO;
	
	@PostMapping(value = "/save")
	public Msg save(@RequestBody SchoolYear year) {
		System.out.println("进入了save方法**************************************************");
		System.out.println(year.getName());
		yearDAO.addYear(year);
		return Msg.success();
	}
	
	@DeleteMapping(value = "/delete")
	public Msg delete(String id) {
		System.out.println("进入了delete方法**************************************************");
		System.out.println(id);
		yearDAO.deleteYear(id);
		return Msg.success();
	}
	
	@GetMapping(value = "/list")
	public Msg list(Map<String, Object> map) {
		map.put("schoolYearList", yearDAO.findAllSchoolYear());
		return Msg.success().add("list", map);
	}
}
