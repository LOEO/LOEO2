package com.loeo.base.exception;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-04-27 11:41:52
 */
public class LoginFailedException extends BizException {
	private String message;

	public LoginFailedException(String message) {
		super(message);
	}
}
