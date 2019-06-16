package com.ssh.answer;

/**
 * @ProjectName: 海康威视业务接入网关
 * @Copyright: 2013 ChongQing Hikvision System Technology Co., Ltd. All Right
 *             Reserved.
 * @address: http://www.hikvision.com
 * @date: 2014-4-28 下午02:31:13
 * @Description: 本内容仅限于重庆海康威视系统技术系统公司内部使用，禁止转发.
 */
public class Result {

	/**
	 * 返回结果标志：默认成功0,失败-1
	 */
	private int ret = 0;

	/**
	 * 返回结果描述：默认操作成功
	 */
	private String msg = "成功";

	public Result(int ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}

	public Result() {
		super();
	}

	public int getRet() {
		return ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
