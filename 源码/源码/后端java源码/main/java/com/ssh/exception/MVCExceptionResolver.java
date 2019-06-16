package com.ssh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;

/**
 * 用于处理spring controller层的异常解析统一处理
 *
 * @author lixinyou 2017/4/15 13:15
 */

//@ControllerAdvice(basePackages = "com.ssh.**.controller**")
public class MVCExceptionResolver extends BaseExceptionHandler<ResponseEntity<CommonRsp<String>>> {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = {
			BusinessException.class, Exception.class })
	public ResponseEntity<CommonRsp<String>> resolveException(Exception ex) {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return resolveException(ex, null);
	}

	public ResponseEntity<CommonRsp<String>> resolveException(Throwable ex, String language) {
		ResponseEntity<CommonRsp<String>> responseEntity;
		CommonRsp<String> resp = new CommonRsp<>();
		Result result = new Result();
		result.setRet(-1);
		resp.setResult(result);
		try {
				String errMsg = handleException(ex, language, "fms.java.common.constant.fail");
				result.setMsg(errMsg);
				responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("failing invoked exception handler:", e);
			result.setMsg("fms.java.common.constant.fail");
			responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
		}
		return responseEntity;
	}
}