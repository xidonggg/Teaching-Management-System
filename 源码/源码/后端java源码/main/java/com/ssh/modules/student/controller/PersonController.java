/**  
 * @Title: PersonController.java  
 * @Package com.ssh.controller  
 * @author Administrator  
 * @date 2018年12月31日  
 * @version V1.0  
 */
package com.ssh.modules.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.ssh.answer.Result;
import com.ssh.exception.EntityNotFoundException;
import com.ssh.modules.finance.entity.FinanceTask;
import com.ssh.modules.staffmanage.staff.entity.Staff;
import com.ssh.modules.student.entity.Person;
import com.ssh.modules.student.service.PersonService;
import com.ssh.vo.Msg;

/**
 * @ClassName: PersonController
 * @Description: TODO
 * @author: Administrator
 * @date: 2018年12月31日 下午3:15:31
 */
@SessionAttributes(value = "username")
@RequestMapping(value = "/person")
@RestController
public class PersonController {

	@Autowired
	public PersonService personService;
	@Autowired
	private TokenToUser tokenToUser;
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/person/distributeClsss
	 * @param classId
	 * @param stuId
	 * @return
	 */
	@PostMapping(value = "/distributeClsss")
	public CommonRsp<String> distributeClsss(String classId,String stuId){
		personService.distributeClsss(classId,stuId);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData("success");
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/person/getStuByClassId
	 * @param classId
	 * @return
	 */
	@PostMapping(value = "/getStuByClassId")
	public CommonRsp<Person> getStuByClassId(String classId){
		List<Person> list = personService.getStudentByClassId(classId);
	    CommonRsp<Person> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(list);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}

	/**
	 * 保存添加的数据
	 * 
	 * @param person
	 * @return
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/person/save
	 * 请求方式：POST
	 * 请求内容：name=admin&phone=18012345678&idCard=441522199911111111&address=guangzhou
	 */
	@PostMapping(value = "/save")
	public Msg save(@RequestBody Person person) {
		if(person.getPicUrl() == null || person.getPicUrl().equals("") || person.getPicUrl().equals("null")) {
			person.setPicUrl("http://localhost:4200/springMVC-spring-hibernate/file/image/de6ce881-3b09-4823-8d63-88ca347f6186.jpg");
		}
		System.out.println("进入了save方法**************************************************");
		System.out.println(person.toString());
		personService.addPerson(person);
		return Msg.success();
	}

	/**
	 * 删除一条数据
	 * 
	 * @param id
	 * @return
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/person/delete?_method=DELETE
	 * 请求方式：POST
	 * 请求内容：id=297ee3fc6804ca04016804cbf79c0000
	 */
	@DeleteMapping(value = "/delete")
	public Msg delete(String id) {
		System.out.println("进入了delete方法**************************************************");
		System.out.println(id);
		personService.deletePersonById(id);
		return Msg.success();
	}

	/**
	 * 更新数据
	 * 
	 * @param person
	 * @return
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/person/update?_method=PUT
	 * 请求方式：POST
	 * 请求内容：id=297ee3fc6804536d01680457541b0001&phone=18012345678&idCard=441522199911111111&address=guangzhou&name=admin
	 */
	@PutMapping(value = "/update")
	public Msg update(@RequestBody Person person) {
		personService.updatePerson(person);
		return Msg.success();
	}

	/**
	 * 查询所有人员信息
	 * 
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/person/list
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/list")
	public Msg list(Map<String, Object> map) {
		map.put("personList", personService.getPersons());
		return Msg.success().add("list", map);
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/person/getPersonByPinyin
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getPersonByPinyin")
	public CommonRsp<Person> getPersonByPinyin(@RequestHeader("token")String token) throws Exception {
		String pinyin = tokenToUser.tokenToLoginUser(token).getPinyin();
		Person person = personService.getPersonByPinyin(pinyin);
	    CommonRsp<Person> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(person);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * 查询人员信息
	 * 
	 * @param id
	 * @param map
	 * @return 
	 * 
	 * 请求地址：http://localhost:9000/springMVC-spring-hibernate/person/get/297ee3fc6804ec26016804ec46f80000
	 * 请求方式：GET
	 * 请求内容：
	 */
	@GetMapping(value = "/get/{id}")
	public Msg get(@PathVariable("id") String id, Map<String, Object> map) {
		Msg msg = new Msg();
		try {
			map.put("personList", personService.getPersonById(id));
			msg = Msg.success().add("list", map);
		}catch(Exception e) {
			msg = Msg.fail().add("list", "失败");
		}
		
		return msg;
//		Person p = null;
//		try {
//			p = personService.getPersonById(id);
//			System.out.println(id);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(p == null) {
//			throw new EntityNotFoundException(id,"person");
//		}
//		return new ResponseEntity<Person>(p,HttpStatus.OK);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Error> entityNotFound(EntityNotFoundException e) {
		String id = e.getId();
		String name = e.getEntityName();
		Error error = new Error(name+" not found");
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

}
