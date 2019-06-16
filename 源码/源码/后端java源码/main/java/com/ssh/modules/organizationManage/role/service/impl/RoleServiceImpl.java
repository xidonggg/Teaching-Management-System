package com.ssh.modules.organizationManage.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.organizationManage.role.dao.impl.RoleDAOImpl;
import com.ssh.modules.organizationManage.role.entity.Authority;
import com.ssh.modules.organizationManage.role.entity.Role;

@Transactional
@Service
public class RoleServiceImpl{
	
	@Autowired
	private RoleDAOImpl auth;
	
//	@Override
	public void addRole(Role role) {
		auth.addRole(role);
		
	}

//	@Override
	public void addAuthority(Authority authority) {
		auth.addAuthority(authority);
		
	}

//	@Override
	public List<Role> getAllRoles() {
		List<Role> r = new ArrayList<Role>();
		r =  auth.getAllRoles();
		System.out.println("role的hashCode");
		for(int i = 0; i < r.size()-1; i++)
		{
			for(int j = i+1; j < r.size(); j++) {
				if(r.get(i).hashCode() == r.get(j).hashCode())
				{
					r.remove(r.get(j));
					j--;
					System.out.println(r.size());
				}
			}
		}
		return r;
	}

//	@Override
	public List<Authority> getAllAuthorities() {
		return auth.getAllAuthorities();
	}

//	@Override
	public void deleteRole(String id) {
		auth.deleteRole(id);
		
	}

//	@Override
	public void updateRole(Role role) {
		auth.updateRole(role);
		
	}

//	@Override
	public Role getRole(String id) {
		return auth.getRole(id);
	}
	
//	public static void main(String args[]) {
//		RoleDAO auth = new RoleDAOImpl();
//		Authority a = new Authority(10,"鏉冮檺","mokuai");
//		List<Authority> aa= new ArrayList<Authority>();
//		aa.add(a);
//		Role role = new Role(10,"瑙掕壊",aa);
//		auth.addRole(role);
//	}

}
