package com.ssh.modules.myclass.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.Util.tokenToUserName.TokenToUser;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.modules.myclass.entity.MyClass;
import com.ssh.modules.myclass.model.RemoteTree;
import com.ssh.modules.myclass.remoteEntity.RemoteSearchClass;
import com.ssh.modules.myclass.service.MyClassService;

@SessionAttributes(value = "username")
@RequestMapping(value = "/myclass")
@RestController
public class MyClassController {
	@Autowired
	private MyClassService myclassService;
	@Autowired
	private TokenToUser tokenToUser;
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/myclass/save
	 * POST
	 * @param myclass
	 * @return
	 */
	@PostMapping(value = "/save")
	public CommonRsp<MyClass> save(@RequestBody MyClass myclass) {
		myclassService.addMyClass(myclass);
	    CommonRsp<MyClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(myclass);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * @param myclass
	 * @return
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/myclass/update?_method=PUT
	 * 请求方式：POST
	 */
	@PutMapping(value = "/update")
	public CommonRsp<MyClass> update(@RequestBody MyClass myclass) {
		myclassService.updateMyClass(myclass);
	    CommonRsp<MyClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(myclass);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/myclass/delete?_method=DELETE
	 * POST
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public CommonRsp<String> delete(String id) {
		myclassService.deleteMyClassById(id);
	    CommonRsp<String> rsp = new CommonRsp<String>();
	    Result r = new Result();
	    rsp.setData("");
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/myclass/list
	 * 请求方式：GET
	 */
	@GetMapping(value = "/list")
	public CommonRsp<MyClass> list(Map<String, Object> map) {
		List<MyClass> ans = myclassService.getMyClasses();
	    CommonRsp<MyClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/myclass/classtree
	 * 请求方式：GET
	 */
	@GetMapping(value = "/classtree")
	public CommonRsp<RemoteTree> getClassTree(Map<String, Object> map) {
		List<RemoteTree> ans = myclassService.getClassTree();
	    CommonRsp<RemoteTree> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/myclass/getMyclasses
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/getMyclasses")
	public CommonRsp<RemoteTree> getMyclasses(@RequestHeader("token")String token) {
		String createPerson = tokenToUser.tokenToLoginUser(token).getPinyin();
		List<RemoteTree> ans =myclassService.getMyclasses(createPerson);
	    CommonRsp<RemoteTree> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/myclass/getTeachClasses
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/getTeachClasses")
	public CommonRsp<RemoteTree> getTeachClasses(@RequestHeader("token")String token) {
		String createPerson = tokenToUser.tokenToLoginUser(token).getPinyin();
		List<RemoteTree> ans =myclassService.getTeachingClasses(createPerson);
	    CommonRsp<RemoteTree> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/myclass/getavaliableClass
	 * 请求方式：GET
	 */
	@GetMapping(value = "/getavaliableClass")
	public CommonRsp<MyClass> getavaliableClass() {
		List<MyClass> ans = myclassService.getAvaliableClass();
	    CommonRsp<MyClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/myclass/searchClass
	 * @param searchclass
	 * @return
	 */
	@PostMapping(value = "/searchClass")
	public CommonRsp<MyClass> SearchClass(@RequestBody RemoteSearchClass searchclass){
		List<MyClass> ans = myclassService.SearchClass(searchclass);
	    CommonRsp<MyClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/myclass/getClassByID
	 * @param searchclass
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/getClassByID")
	public CommonRsp<MyClass> getClassByID(String classId) throws Exception{
		MyClass ans = myclassService.getMyClassById(classId);
	    CommonRsp<MyClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(ans);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
}
