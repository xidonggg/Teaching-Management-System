package com.ssh.exception;

public class EntityNotFoundException extends RuntimeException{
	private String id;
	private String entityName;
	
	public EntityNotFoundException(String id,String name) {
		this.id = id;
		this.entityName = name;
	}
	public String getId() {
		return id;
	}
	public String getEntityName() {
		return entityName;
	}

}
