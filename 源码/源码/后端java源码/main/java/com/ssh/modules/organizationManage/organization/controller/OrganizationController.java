package com.ssh.modules.organizationManage.organization.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.modules.organizationManage.organization.entity.Org;
import com.ssh.modules.organizationManage.organization.entity.Organization;
import com.ssh.modules.organizationManage.organization.remoteEntity.remoteOrganization;
import com.ssh.modules.organizationManage.organization.service.OrganizationService;
import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.vo.Msg;

@SessionAttributes(value = "username")
@RequestMapping(value = "/organization")
@RestController
public class OrganizationController {
	@Autowired
	public OrganizationService organizationService;

	/**
	 * 查询组织结构
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/organization/list
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/list")
	public Msg list(Map<String, Object> map) {
		System.out.println("进入了list方法**************************************************");
		List<Organization> organizations = new LinkedList<Organization>();
		organizations.add(organizationService.getOrganization());
		map.put("organizationList", organizations);
		return Msg.success().add("list", map);
	}
	/**
	 * 查询人员信息
	 * 
	 * @param id
	 * @param map
	 * @return 
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/organization/get/1
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/get/{id}")
	public Msg get(@PathVariable("id") int id, Map<String, Object> map) {
		Msg msg = new Msg();
		try {
			map.put("organizationList", organizationService.getOrganizationById(id));
			msg = Msg.success().add("list", map);
		}catch(Exception e) {
			msg = Msg.fail().add("list", "失败");
		}
		
		return msg;
	}
	
	/**
	 * 查询校区
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/organization/getschool
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/getschool")
	public Msg getschool(Map<String, Object> map) {
		System.out.println("进入了getschool方法**************************************************");
		List<Org> orgs = new LinkedList<Org>();
		orgs.addAll(organizationService.getSchools());
		map.put("getschoolList", orgs);
		return Msg.success().add("list", map);
	}

	/**
	 * 查询校区
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/organization/getorganizationTree
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/getorganizationTree")
	public CommonRsp<remoteOrganization> getorganizationTree(Map<String, Object> map) {
		System.out.println("进入了getorganizationTree方法**************************************************");
		remoteOrganization ans = organizationService.getorganizationTree();
		
	    CommonRsp<remoteOrganization> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(ans);
	    rsp.setType("date");
	    rsp.setResult(r);
		return rsp;
	}
}
