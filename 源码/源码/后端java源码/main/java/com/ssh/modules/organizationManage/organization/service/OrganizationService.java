package com.ssh.modules.organizationManage.organization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.modules.organizationManage.organization.dao.OrganizationDAO;
import com.ssh.modules.organizationManage.organization.entity.Org;
import com.ssh.modules.organizationManage.organization.entity.Organization;
import com.ssh.modules.organizationManage.organization.remoteEntity.remoteOrganization;


@Transactional
@Service
public class OrganizationService {
	
	@Autowired
	public OrganizationDAO organizationDAO;
		
	private List<Org> orgs;
	private Map<Integer,Organization> organizations;
	private Queue<Organization> queue = new LinkedList<Organization>();
	private Organization organization;
	private void getMapOrganizations() {
		orgs = organizationDAO.getOrgs();
		organizations = new HashMap<Integer,Organization>();
		for(int i = 0; i < orgs.size(); i++) {
			
			Organization o = Organization.OrgToOrganization(orgs.get(i));
			organizations.put(o.getId(), o);
		}
		
		//获得childIds
		for(int i = 1; i < organizations.size(); i++) {
			int parent = organizations.get(i).getParentId();
			organizations.get(parent).getChildIds().add(organizations.get(i).getId());
		}
	}
	private void arrayToTree(int id){

		//根据childIds获取orginizations
		organization = organizations.get(id);
		for (int i = 0; i < organization.getChildIds().size(); i++) {
			int temp = organization.getChildIds().get(i);
			organization.getOrganization().add(organizations.get(temp));//temp应该是id而不是下标
			queue.add(organizations.get(temp));
		}
		while(!queue.isEmpty()) {
			Organization temp = queue.poll();
			for (int i = 0; i < temp.getChildIds().size(); i++) {
				int tempchild = temp.getChildIds().get(i);
				temp.getOrganization().add(organizations.get(tempchild));
				queue.add(organizations.get(tempchild));
			}
		}
	}
	public Organization getOrganization() {
		getMapOrganizations();
		arrayToTree(0);
		return this.organization;
	}
	
	public Organization getOrganizationById(int id) throws Exception {
//		throw new Exception();
		getMapOrganizations();
		arrayToTree(id);
		return this.organization;
	}
	
	public List<Org> getSchools() {
		List<Org> schools = new ArrayList<Org>();
		orgs = organizationDAO.getOrgs();
		for(int i = 0; i < orgs.size(); i++) {
			if(orgs.get(i).getParentId() == 0) {
				schools.add(orgs.get(i));
			}
		}
		return schools;
	}
	
	public remoteOrganization getorganizationTree(){
		//List<remoteOrganization> schools = new ArrayList<remoteOrganization>();
		//获取组织树，organization
		getOrganization();
		Queue<Organization> queue = new LinkedList<Organization>();
		//先加入一个节点
		queue.add(organization);
		Queue<remoteOrganization> queue2 = new LinkedList<remoteOrganization>();
		
		remoteOrganization remote = 
				new remoteOrganization(organization.getName(),String.valueOf(organization.getId()),true,"anticon anticon-smile-o",new ArrayList<remoteOrganization>(),false);
		queue2.add(remote);
		
		ArrayList<remoteOrganization> newarray = new ArrayList<remoteOrganization>();
		while(!queue.isEmpty()) {
			Organization temp = (Organization) queue.poll();
			remoteOrganization temp2 = (remoteOrganization) queue2.poll();
			ArrayList<remoteOrganization> tempchildrens = (ArrayList<remoteOrganization>) remoteOrganization.remoteOrganizationToOrganization(temp.getOrganization());
			temp2.setChildren(tempchildrens);
			
			for(int i = 0; i < temp.getOrganization().size(); i++) {
				queue.add(temp.getOrganization().get(i));
				queue2.add(temp2.getChildren().get(i));
			}
		}
		
		System.out.println("remote:");
		return remote;
	}
}
