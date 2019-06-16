package com.ssh.modules.organizationManage.role.dao;

import java.util.List;

import com.ssh.modules.organizationManage.role.entity.Authority;
import com.ssh.modules.organizationManage.role.entity.Role;

public interface RoleDAO {
	
	public List<Authority> getAllAuthorities();
	
	public List<Role> getAllRoles();
	
	public void addRole(Role role);
	
	public void addAuthority(Authority authority);
	
	public void deleteRole(String id);
	
	public void updateRole(Role role);

	public Role getRole(String id);
}
