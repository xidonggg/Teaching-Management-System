package com.ssh.exception;

/**
 * <p>异常处理接口</p>
 *
 * @author yanbin 2019/5/4 13:15
 */

public interface ExceptionHandler<T> {


	/**
	 * 异常解析接口
	 */
	T resolveException(Throwable ex, String language) throws Throwable;
}
