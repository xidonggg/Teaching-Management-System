package com.ssh.answer;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 远程接口统一返回实体类
 * </p>
 *
 * @param <T>
 * @author hujianfeng 2016年5月12日 下午3:06:20
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2016年5月12日
 * @modify by reason:{方法名}:{原因}
 */
public class CommonRsp<T> {
	
	private String token;

	/**
	 * 返回结果
	 */
	private Result result;

	/**
	 * 返回数据类型，"data"：单个数据，"list"：列表数据，"page":分页数据
	 */
	private String type;

	/**
	 * 返回单个数据
	 */
	private T data;

	/**
	 * 返回列表数据
	 */
	private List<T> list;

	/**
	 * 返回分页数据
	 */
	private Page<T> page;

	private Map<String, Object> msgMap;

	public Result getResult() {
		return result;
	}

	public String getType() {
		return type;
	}

	public T getData() {
		return data;
	}

	public List<T> getList() {
		return list;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "CommonRsp [result=" + result + ", type=" + type + ", data=" + data + ", list=" + list + ", page=" + page + "]";
	}

	public Map<String, Object> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, Object> msgMap) {
		this.msgMap = msgMap;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
