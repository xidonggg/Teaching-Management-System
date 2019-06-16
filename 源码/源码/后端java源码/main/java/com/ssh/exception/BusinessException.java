package com.ssh.exception;

/*
 * 业务异常处理类
 */
import java.util.Map;
public class BusinessException extends RuntimeException {
	
	static final long serialVersionUID = -2115826936519503708L;

	private String				errCode;
								
	private String				errMsg;
	private Map<String, Object>	map;
								
	public BusinessException(String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;
	}
	
	public BusinessException(String errMsg, Map<String, Object> map) {
		super(errMsg);
		this.errMsg = errMsg;
		this.map = map;
	}
	
	public BusinessException(String errCode, String errMsg, Map<String, Object> map) {
		super(errMsg);
		this.errMsg = errMsg;
		this.errCode = errCode;
		this.map = map;
	}
	
	public BusinessException(String errCode, String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;
		this.errCode = errCode;
	}
	
	public BusinessException(String errCode, Throwable ex) {
		super(ex);
		this.errCode = errCode;
	}
	
	public BusinessException(String errCode, String errMsg, Throwable ex) {
		super(errMsg, ex);
		this.errMsg = errMsg;
		this.errCode = errCode;
	}
	
	public String getErrCode() {
		return errCode;
	}
	
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
