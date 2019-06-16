package com.ssh.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * {@link ExceptionHandler}的抽象实现类，封装异常处理行为公共逻辑。主要用于处理
 * {@link BusinessException}发生时获取具体的异常信息
 *
 */

public abstract class BaseExceptionHandler<T> implements ExceptionHandler<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	public String handleException(Throwable ex, String language, String defaultErrMsg) {
		logger.error("Failed to handle request:", ex);
		StringBuilder finalErr = new StringBuilder();
		if (ex instanceof BusinessException) {
			BusinessException busEx = (BusinessException) ex;
			String errMsg = busEx.getErrMsg();
			finalErr.append(errMsg);
		}else if (ex instanceof Exception) {
			String errMsg = "exception";
			if (null == errMsg) {
				errMsg = defaultErrMsg;
			}
			finalErr.append(errMsg);
		}
		logger.info("error msg:{}", finalErr.toString());
		return finalErr.toString();
	}
}