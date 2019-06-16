package com.ssh.login.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "redisUse")
public class RedisUse {
	private String id;
	private String key;
	private String value;
	private String cresteTime;
	
	public RedisUse() {
		super();
	}

	public RedisUse(String id, String key, String value, String cresteTime) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
		this.cresteTime = cresteTime;
	}

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "key", nullable = false, unique = true)
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name = "value", nullable = false, unique = true)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "cresteTime", nullable = false, length = 32)
	public String getCresteTime() {
		return cresteTime;
	}
	public void setCresteTime(String cresteTime) {
		this.cresteTime = cresteTime;
	}


}
