package com.ssh.login.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.authorityannotation.AuthAspect;
import com.ssh.authorityannotation.AuthFilter;
import com.ssh.exception.BusinessException;
import com.ssh.login.bean.UserLogin;
import com.ssh.login.service.UserLoginService;
import com.ssh.login.utils.TokenManager;

@RequestMapping(value = "/userlogin")
@RestController
public class UserController {
	@Autowired
	private TokenManager manager;
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/userlogin/login
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	public CommonRsp<UserLogin> listname(@RequestBody UserLogin userLogin) throws Exception{

		UserLogin newuserLogin = userLoginService.logincheck(userLogin.getPinyin(), userLogin.getPassword());
		String token = manager.createToken(newuserLogin.getId());
	    CommonRsp<UserLogin> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setToken(token);
	    rsp.setData(newuserLogin);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/userlogin/loginout
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/loginout")
	public CommonRsp<String> listname(@RequestHeader("token")String token) throws Exception{

		Boolean bb = manager.clearToken(token);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setToken(token);
	    rsp.setData(bb.toString());
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	@GetMapping(value = "/login2")
	//@AuthFilter(needAuth = "neeeeeeeed")
	public CommonRsp<String> listname(Map<String, Object> map, @RequestHeader("token")String token) {
		
//		System.out.println("token:"+token);
//		String token3 = manager.createToken(1);
//		System.out.println("新增token:"+token3);
//		Boolean b = manager.checkToken(token);
//		System.out.println("检车是否登陆:"+b);
//		Boolean bb = manager.clearToken(token);
//		System.out.println("检车是否删除成功:"+bb);
		
		
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(token);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
}
