package com.ssh.modules.staffmanage.staff.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.Util.tokenToUserName.TokenToUser;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Page;
import com.ssh.answer.Result;
import com.ssh.authorityannotation.AuthAspect;
import com.ssh.exception.BusinessException;
import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.modules.staffmanage.staff.service.StaffService;
import com.ssh.modules.student.entity.Person;
import com.ssh.vo.Msg;

@SessionAttributes(value = "username")
@RequestMapping(value = "/staff")
@RestController
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	@Autowired
	private TokenToUser tokenToUser;
	@PostMapping(value = "/save")
	public CommonRsp<Staff> save(@RequestBody Staff staff) {
		if(staff.getPicUrl() == null || staff.getPicUrl().equals("") || staff.getPicUrl().equals("null")) {
			staff.setPicUrl("http://localhost:4200/springMVC-spring-hibernate/file/image/de6ce881-3b09-4823-8d63-88ca347f6186.jpg");
		}
		staffService.addStaff(staff);
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(staff);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 更新数据
	 * 
	 * @param person
	 * @return
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/staff/update?_method=PUT
	 * 请求方式：POST
	 * 请求内容：id=297ee3fc6804536d01680457541b0001&phone=18012345678&idCard=441522199911111111&address=guangzhou&name=admin
	 */
	@PutMapping(value = "/update")
	public CommonRsp<Staff> update(@RequestBody Staff staff) {
		staffService.updateStaff(staff);
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(staff);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	
	@DeleteMapping(value = "/delete")
	public CommonRsp<String> delete(String id) {
		staffService.deleteStaffById(id);
	    CommonRsp<String> rsp = new CommonRsp<String>();
	    Result r = new Result();
	    rsp.setData("");
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 查询所有人员信息
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/staff/list
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/list")
	public CommonRsp<Staff> list(Map<String, Object> map) {
		List<Staff> ans = staffService.getStaffs();
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 查询所有人员信息
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/staff/pagelist
	 * 请求方式：GET
	 * 请求内容：
	 */
	@PostMapping(value = "/pagelist")
	public CommonRsp<Staff> pagelist(int page) {
	    Page<Staff> p = new Page<Staff>();
		List<Staff> ans = staffService.getStaffs((page-1)*p.getSize(),p.getSize());
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    int a = staffService.getStaffNum();
	    int b = p.getSize();
	    int c = a%b == 0 ? (a/b) : (a/b)+1;
	    p.setCurrent(page);
	    p.setTotal(a);
	    p.setList(ans);
	    rsp.setPage(p);
	    rsp.setType("page");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/getPageStaffByDepartmentId
	 * @param departmentId
	 * @return
	 */
	@PostMapping(value = "/getPageStaffByDepartmentId")
	public CommonRsp<Staff> getPageStaffByDepartmentId(String departmentId,int page) {
		Page<Staff> p = new Page<Staff>();
		List<Staff> ans = staffService.getPageStaffByDepartmentId(departmentId, (page-1)*p.getSize(),p.getSize());
	    int a = staffService.getStaffByDepartmentId(departmentId).size();
	    int b = p.getSize();
	    int c = a%b == 0 ? (a/b) : (a/b)+1;
	    p.setCurrent(page);
	    p.setTotal(a);
	    p.setList(ans);
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("page");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/getMyDepartment
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/getMyDepartment")
	public CommonRsp<String> getMyDepartment(@RequestHeader("token")String token) {
		String pinyin = tokenToUser.tokenToLoginUser(token).getPinyin();
		Staff staff = staffService.getStaffByPinyin(pinyin);
		String[] anss = staff.getDepartments().split(",");
		List<String> ans = Arrays.asList(anss);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/listname
	 * GET
	 * @param map
	 * @return
	 */
	@GetMapping(value = "/listname")
	public CommonRsp<String> listname(Map<String, Object> map) {
		List<String> ans = staffService.getStaffNames();
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/getStaffByDepartmentId
	 * @param departmentId
	 * @return
	 */
	@PostMapping(value = "/getStaffByDepartmentId")
	public CommonRsp<Staff> getStaffByDepartmentId(String departmentId) {
		List<Staff> ans = staffService.getStaffByDepartmentId(departmentId);
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}

	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/stateChange
	 * @param staff
	 * @return
	 */
	@PostMapping(value = "/stateChange")
	public CommonRsp<Staff> stateChange(@RequestBody Staff staff) {
		staffService.stateChange(staff.getId(),staff.getPinyin(),staff.getState());
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(staff);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}

	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/getStaffById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getStaffById")
	public CommonRsp<Staff> getStaffById(String id) throws Exception {
		Staff staff = staffService.getStaffById(id);
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(staff);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/staff/getStaffByPinyin
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getStaffByPinyin")
	public CommonRsp<Staff> getStaffByPinyin(@RequestHeader("token")String token) throws Exception {
		String pinyin = tokenToUser.tokenToLoginUser(token).getPinyin();
		Staff staff = staffService.getStaffByPinyin(pinyin);
	    CommonRsp<Staff> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(staff);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
}
