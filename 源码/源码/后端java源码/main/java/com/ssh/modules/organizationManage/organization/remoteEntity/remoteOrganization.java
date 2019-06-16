package com.ssh.modules.organizationManage.organization.remoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ssh.modules.organizationManage.organization.entity.Organization;

public class remoteOrganization {
	/**
	 * title用name标识*/
	private String title;
	/**
	 * key用id标识
	 */
	private String key;
	private boolean expanded;
	private String icon;
	private ArrayList<remoteOrganization> children;
	private boolean isLeaf;
	remoteOrganization(){
		
	}
	public remoteOrganization(String title, String key, boolean expanded, String icon, ArrayList<remoteOrganization> children,
			boolean isLeaf) {
		super();
		this.title = title;
		this.key = key;
		this.expanded = expanded;
		this.icon = icon;
		this.children = children;
		this.isLeaf = isLeaf;
	}
	public static ArrayList<remoteOrganization>remoteOrganizationToOrganization(Vector<Organization> organizations){
		ArrayList<remoteOrganization> ans = new ArrayList<remoteOrganization>();
		for(int i = 0; i < organizations.size(); i++) {
			Organization org = organizations.get(i);
			remoteOrganization temp = new remoteOrganization(org.getName(),String.valueOf(org.getId()),false,"anticon anticon-book-o",new ArrayList<remoteOrganization>(), false);
			if(org.getOrganization() == null || org.getOrganization().size() == 0)
			{
				temp.setIsLeaf(true);
			}
			ans.add(temp);
		}
		return ans;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public ArrayList<remoteOrganization> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<remoteOrganization> children) {
		this.children = children;
	}
	public boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
