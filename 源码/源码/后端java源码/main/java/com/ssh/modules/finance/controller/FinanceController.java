package com.ssh.modules.finance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.Util.stringToList.StringToList;
import com.ssh.Util.timeFormat.TimeFormat;
import com.ssh.Util.tokenToUserName.TokenToUser;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.modules.finance.entity.FinanceOrder;
import com.ssh.modules.finance.entity.FinanceTask;
import com.ssh.modules.finance.remoteEntity.RemoteFinance;
import com.ssh.modules.finance.remoteEntity.RemoteFinanceOrder;
import com.ssh.modules.finance.service.FinanceService;

@RequestMapping(value = "/finance")
@RestController
public class FinanceController {
	@Autowired
	private FinanceService financeService;
	@Autowired
	private TokenToUser tokenToUser;
	/**
	 * 新建财政任务
	 * http://localhost:8081/springMVC-spring-hibernate/finance/saveFinance
	 */
	@PostMapping(value = "/saveFinance")
	public CommonRsp<FinanceTask> saveFinance(@RequestBody FinanceTask task,@RequestHeader("stuIds")String stuIds, @RequestHeader("token")String token) {
		task.setCreatePerson(tokenToUser.tokenToLoginUser(token).getPinyin());
		String timenow = TimeFormat.stampToTime(new Date());
		task.setCreateTime(timenow);
		task.setState("未发布");
		task.setPaidNumber(0);
		task.setStateChangeTime(timenow);
		String[] stuId = StringToList.stringToList(stuIds);
		task.setUnpaidNumber(stuId.length);
		financeService.addTask(task, stuId);
	    CommonRsp<FinanceTask> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(task);
	    rsp.setType("data");
	    rsp.setResult(r); 
		return rsp;
	}
	/**
	 * 获得全部任务
	 * http://localhost:8081/springMVC-spring-hibernate/finance/getFinance
	 */
	@GetMapping(value="/getFinance")
	public CommonRsp<FinanceTask> getFinance(){
		List<FinanceTask> tasks = financeService.getTasks();
	    CommonRsp<FinanceTask> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(tasks);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 获得全部任务
	 * http://localhost:8081/springMVC-spring-hibernate/finance/getFinanceById
	 */
	@PostMapping(value="/getFinanceById")
	public CommonRsp<RemoteFinance> getFinanceById(String id){
		RemoteFinance data = financeService.getRemoteTaskById(id);
	    CommonRsp<RemoteFinance> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(data);
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 改变财务状态-停止
	 * http://localhost:8081/springMVC-spring-hibernate/finance/financeStop
	 */
	@PostMapping(value="/financeStop")
	public CommonRsp<String> financeStop(String id){
		financeService.financeStop(id);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData("success");
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 改变财务状态
	 * http://localhost:8081/springMVC-spring-hibernate/finance/changeFinanceState
	 */
	@PostMapping(value = "/changeFinanceState")
	public CommonRsp<FinanceTask> changeFinanceState(@RequestBody FinanceTask task){
		financeService.changestateStack(task.getId(), task.getState());
	    CommonRsp<FinanceTask> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(task);
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 改变财务状态
	 * http://localhost:8081/springMVC-spring-hibernate/finance/changeOrderState
	 */
	@PostMapping(value = "/changeOrderState")
	public CommonRsp<RemoteFinanceOrder> changeOrderState(@RequestBody RemoteFinanceOrder order){
		financeService.changestateOrder(order.getId(), order.getState());
	    CommonRsp<RemoteFinanceOrder> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(order);
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 删除财务
	 * http://localhost:8081/springMVC-spring-hibernate/finance/deleteFinance
	 */
	@PostMapping(value = "/deleteFinance")
	public CommonRsp<String> deleteFinance(String id){
		financeService.deleteTask(id);
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData("删除成功");
	    rsp.setType("data");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 获得全部订单
	 * http://localhost:8081/springMVC-spring-hibernate/finance/getFinanceOrders
	 */
	@GetMapping(value="/getFinanceOrders")
	public CommonRsp<RemoteFinanceOrder> getFinanceOrders(){
		List<RemoteFinanceOrder> tasks = financeService.getOrders();
	    CommonRsp<RemoteFinanceOrder> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(tasks);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	/**
	 * 获得个人全部订单
	 * http://localhost:8081/springMVC-spring-hibernate/finance/getFinanceOrdersByPerson
	 */
	@GetMapping(value="/getFinanceOrdersByPerson")
	public CommonRsp<RemoteFinanceOrder> getFinanceOrdersByPerson(@RequestHeader("token")String token){
		String pinyin[] = token.split("_");
		List<RemoteFinanceOrder> tasks = financeService.getOrdersByPerson(pinyin[0]);
	    CommonRsp<RemoteFinanceOrder> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(tasks);
	    rsp.setType("list");
	    rsp.setResult(r);
	    return rsp;
	}
	
}
