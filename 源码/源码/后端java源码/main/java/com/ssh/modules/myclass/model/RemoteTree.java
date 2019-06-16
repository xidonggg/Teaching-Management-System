package com.ssh.modules.myclass.model;

import java.util.ArrayList;
import java.util.List;

import com.ssh.modules.myclass.entity.MyClass;

public class RemoteTree {
	private String key;
	private String title;
	private List<RemoteTree> children;
	private boolean isLeaf;
	private boolean expanded;
	public RemoteTree() {
		this.title = "";
		this.key = "";
		this.children = new ArrayList<RemoteTree>();
		this.isLeaf = true;
		this.expanded = true;
	}
	public RemoteTree(String key, String title, List<RemoteTree> children, boolean isLeaf, boolean expanded) {
		super();
		this.key = key;
		this.title = title;
		this.children = children;
		this.isLeaf = isLeaf;
		this.expanded = expanded;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<RemoteTree> getChildren() {
		return children;
	}
	public void setChildren(List<RemoteTree> children) {
		this.children = children;
	}
	public boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public boolean getIsExpanded() {
		return expanded;
	}
	public void setIsExpanded(boolean expanded) {
		this.expanded = expanded;
	}

}
