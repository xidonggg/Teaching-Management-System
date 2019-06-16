package com.ssh.modules.course.controller;

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
import com.ssh.modules.course.entity.Course;
import com.ssh.modules.course.entity.CourseClass;
import com.ssh.modules.course.remoteEntity.RemoteCourseClass;
import com.ssh.modules.course.service.CourseService;


@SessionAttributes(value = "username")
@RequestMapping(value = "/course")
@RestController
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private TokenToUser tokenToUser;
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/course/save
	 * @param course
	 * @return
	 */
	@PostMapping(value = "/save")
	public CommonRsp<Course> save(@RequestBody Course course) {
		course.setEstablishPerson("admin");
		course.setEstablishTime(new Date().toString());
		String teachersstr = course.getTeachers();
		if(teachersstr == null)
			throw new BusinessException("教师不能为空");
		String[] teachers = teachersstr.split(",");
		String[] pinyin = teachers[0].split("/");
		course.setMajorTeachers(pinyin[0]);
		courseService.addCourse(course);
	    CommonRsp<Course> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(course);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * @param person
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/course/update?_method=PUT
	 * 请求方式：POST
	 */
	@PutMapping(value = "/update")
	public CommonRsp<Course> update(@RequestBody Course course) {
		courseService.updateCourse(course);
	    CommonRsp<Course> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(course);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/course/delete?_method=DELETE
	 * 请求方式：GET
	 */
	@DeleteMapping(value = "/delete")
	public CommonRsp<String> delete(String id) {
		courseService.deleteCourseById(id);
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
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/course/get/297ee3fc6804ec26016804ec46f80000
	 * 请求方式：GET
	 */
	@GetMapping(value = "/get/{id}")
	public CommonRsp<Course> get(@PathVariable("id") String id) {
		Course course;
		try {
			course = courseService.getCourseById(id);
		} catch (Exception e) {
			throw new BusinessException("找不到改课程");
		}
	    CommonRsp<Course> rsp = new CommonRsp<Course>();
	    Result r = new Result();
	    rsp.setData(course);
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * @param map
	 * @return
	 * 
	 * 请求地址：http://localhost:8081/springMVC-spring-hibernate/course/list
	 * 请求方式：GET
	 */
	@GetMapping(value = "/list")
	public CommonRsp<Course> list(Map<String, Object> map) {
		List<Course> ans = courseService.getCourses();
	    CommonRsp<Course> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);;
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/course/getCourseClass
	 * @return
	 */
	@GetMapping(value = "/getCourseClass")
	public CommonRsp<RemoteCourseClass> getCourseClass(){
		List<RemoteCourseClass> getCourseClasses = courseService.getCourseClasses();
	    CommonRsp<RemoteCourseClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(getCourseClasses);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/course/getcourseClassSelectByClassId
	 * @param classId
	 * @param courseIds
	 * @param token
	 * @return
	 */
	@PostMapping(value = "/getcourseClassSelectByClassId")
	public CommonRsp<RemoteCourseClass> getcourseClassSelectByClassId(String classId){
		List<RemoteCourseClass> getCourseClasses = courseService.getCourseClassesByClass(classId);
	    CommonRsp<RemoteCourseClass> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(getCourseClasses);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/course/courseClassSelect
	 * @param classId
	 * @param courseIds
	 * @param token
	 * @return
	 */
	@PostMapping(value = "/courseClassSelect")
	public CommonRsp<String> courseClassSelect(String classId, String courseIds,@RequestHeader("token")String token){
		String createPerson = tokenToUser.tokenToLoginUser(token).getPinyin();
		String timenow = TimeFormat.stampToTime(new Date());
		courseService.setCourseClass(classId, courseIds, createPerson, timenow);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData("success");
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/course/deleteCourseClassSelect
	 * @param id
	 * @param token
	 * @return
	 */
	@PostMapping(value = "/deleteCourseClassSelect")
	public CommonRsp<String> deleteCourseClassSelect(String id,@RequestHeader("token")String token){
		courseService.deleteCourseClassSelect(id);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData("success");
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/course/getMyCourse
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/getMyCourse")
	public CommonRsp<Course> getMyCourse(@RequestHeader("token")String token){
		String pinyin = tokenToUser.tokenToLoginUser(token).getPinyin();
		List<Course> course = courseService.getMyCourse(pinyin);
	    CommonRsp<Course> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(course);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	
}
