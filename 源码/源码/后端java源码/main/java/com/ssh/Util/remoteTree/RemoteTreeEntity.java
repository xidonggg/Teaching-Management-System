package com.ssh.Util.remoteTree;

import java.util.ArrayList;


public class RemoteTreeEntity<T> {
	/**
	 * title用name标识*/
	private String title;
	/**
	 * key用id标识
	 */
	private String key;
	private boolean expanded;
	private String icon;
	private ArrayList<RemoteTreeEntity<T>> children;
	private boolean isLeaf;
	private T t;
	
	public RemoteTreeEntity() {
		super();
	}
	
	public RemoteTreeEntity(String title, String key, boolean expanded, String icon,
			ArrayList<RemoteTreeEntity<T>> children, boolean isLeaf, T t) {
		super();
		this.title = title;
		this.key = key;
		this.expanded = expanded;
		this.icon = icon;
		this.children = children;
		this.isLeaf = isLeaf;
		this.t = t;
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
	public boolean isExpanded() {
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
	public ArrayList<RemoteTreeEntity<T>> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<RemoteTreeEntity<T>> children) {
		this.children = children;
	}
	public boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	
}
