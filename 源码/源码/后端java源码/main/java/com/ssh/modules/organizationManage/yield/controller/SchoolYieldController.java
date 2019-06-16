package com.ssh.modules.organizationManage.yield.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.modules.organizationManage.yield.entity.SchoolYield;
import com.ssh.modules.organizationManage.yield.service.SchoolYieldService;
import com.ssh.vo.Msg;

@SessionAttributes(value = "username")
@RequestMapping(value = "/schoolyield")
@RestController
public class SchoolYieldController {

	@Autowired
	private SchoolYieldService schoolYieldService;
	/**
	 * 保存添加的数据
	 * 
	 * @param person
	 * @return
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/schoolyield/save
	 * 请求方式：POST
	 * 请求内容：name=admin&phone=18012345678&idCard=441522199911111111&address=guangzhou
	 */
	@PostMapping(value = "/save")
	public Msg save(@RequestBody SchoolYield schoolYield) {
		System.out.println("进入了save方法**************************************************");
		System.out.println(schoolYieldService.toString());
		schoolYieldService.addSchoolYield(schoolYield);
		return Msg.success();
	}
	/**
	 * 删除一条数据
	 * 
	 * @param id
	 * @return
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/schoolyield/delete?_method=DELETE
	 * 请求方式：POST
	 * 请求内容：id=297ee3fc6804ca04016804cbf79c0000
	 */
	@DeleteMapping(value = "/delete")
	public Msg delete(String id) {
		System.out.println("进入了delete方法**************************************************");
		System.out.println(id);
		schoolYieldService.deleteSchoolYieldById(id);
		return Msg.success();
	}
	/**
	 * 查询所有人员信息
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/schoolyield/list
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/list")
	public Msg list(Map<String, Object> map) {
		map.put("schoolYieldList", schoolYieldService.getSchoolYield());
		return Msg.success().add("list", map);
	}
}
