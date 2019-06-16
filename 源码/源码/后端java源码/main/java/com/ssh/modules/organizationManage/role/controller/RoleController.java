package com.ssh.modules.organizationManage.role.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ssh.modules.organizationManage.role.entity.Authority;
import com.ssh.modules.organizationManage.role.entity.Role;
import com.ssh.modules.organizationManage.role.service.impl.RoleServiceImpl;
import com.ssh.vo.Msg;

@SessionAttributes(value = "username")
@RequestMapping(value = "/role")
@RestController
public class RoleController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	/**
	 * ��ѯ���н�ɫ��Ϣ
	 * 
	 * @param map
	 * @return
	 * 
	 * �����ַ��http://localhost:8081/springMVC-spring-hibernate/role/getAllRoles
	 * ����ʽ��GET
	 * �������ݣ�
	 */
	@GetMapping(value = "/getAllRoles")
	public Msg getAllRoles(Map<String, Object> map) {
		List<Role> r = roleService.getAllRoles();
		map.put("roleList", r);
		return Msg.success().add("list", map);
	}
	/**
	 * ��ѯ����Ȩ����Ϣ
	 * 
	 * @param map
	 * @return
	 * 
	 * �����ַ��http://localhost:8081/springMVC-spring-hibernate/role/getAllAuthorities
	 * ����ʽ��GET
	 * �������ݣ�
	 */
	@GetMapping(value = "/getAllAuthorities")
	public Msg getAllAuthorities(Map<String, Object> map) {
		map.put("authorityList", roleService.getAllAuthorities());
		return Msg.success().add("list", map);
	}
	/**
	 * ��ѯ��ɫ��Ϣ
	 * 
	 * @param id
	 * @param map
	 * @return 
	 * 
	 * �����ַ��http://localhost:8081/springMVC-spring-hibernate/role/getRole/297ee3fc6804ec26016804ec46f80000
	 * ����ʽ��GET
	 * �������ݣ�
	 */
	@GetMapping(value = "/getRole/{id}")
	public Msg get(@PathVariable("id") String id, Map<String, Object> map) {
		Msg msg = new Msg();
		try {
			map.put("personList", roleService.getRole(id));
			msg = Msg.success().add("list", map);
		}catch(Exception e) {
			msg = Msg.fail().add("list", "ʧ��");
		}
		
		return msg;
	}
	/**
	 * ���½�ɫ����
	 * 
	 * @param person
	 * @return
	 * 
	 * �����ַ��http://localhost:8081/springMVC-spring-hibernate/role/updateRole?_method=PUT
	 * ����ʽ��POST
	 * �������ݣ�id=297ee3fc6804536d01680457541b0001&phone=18012345678&idCard=441522199911111111&address=guangzhou&name=admin
	 */
	@PutMapping(value = "/updateRole")
	public Msg update(@RequestBody Role role) {
		roleService.updateRole(role);
		return Msg.success();
	}

	/**
	 * ɾ��һ������
	 * 
	 * @param id
	 * @return
	 * 
	 * �����ַ��http://localhost:9000/springMVC-spring-hibernate/person/deleteRole?_method=DELETE
	 * ����ʽ��POST
	 * �������ݣ�id=297ee3fc6804ca04016804cbf79c0000
	 */
	@DeleteMapping(value = "/deleteRole")
	public Msg delete(String id) {
		System.out.println("������delete����**************************************************");
		System.out.println(id);
		roleService.deleteRole(id);
		return Msg.success();
	}
	/**
	 * ��ӽ�ɫ
	 * 
	 * @param person
	 * @return
	 * 
	 * �����ַ��http://localhost:8081/springMVC-spring-hibernate/role/addRole
	 * ����ʽ��POST
	 * �������ݣ�name=admin&phone=18012345678&idCard=441522199911111111&address=guangzhou
	 */
	@PostMapping(value = "/addRole")
	public Msg save(@RequestBody Role role) {
		System.out.println("������save����**************************************************");
		System.out.println(role.getAuthorities().size());
		roleService.addRole(role);
		return Msg.success();
	}
	/**
	 * ��ӽ�ɫ
	 * 
	 * @param person
	 * @return
	 * 
	 * �����ַ��http://localhost:8081/springMVC-spring-hibernate/role/addRole
	 * ����ʽ��POST
	 * �������ݣ�id=297ee3fc6804536d01680457541b0001&phone=18012345678&idCard=441522199911111111&address=guangzhou&name=admin
	 */
	//http://localhost:8081/springMVC-spring-hibernate/role/add
	@GetMapping(value = "/add")
	public Msg get( Map<String, Object> map) {
//		Authority aa = new Authority();
//		aa.setUnderMoudle("ѧ��ģ��");
//		aa.setName("��ȡȨ��");
		//roleService.addAuthority(aa);
		List<Authority> aa = roleService.getAllAuthorities();
		
//		ArrayList<Authority> a = new ArrayList<Authority>() {
//			{
//				add(aa);
//			}
//		};
		ArrayList<Authority> a = new ArrayList<Authority>(aa);
		Role role = new Role("","��ɫ5",a);
		roleService.addRole(role);
		return Msg.success().add("list", map);
	}

}
