package com.loeo.exception;

/**
 * Created by LOEO on 2017/06/14 22:45
 */
public class UsernameAlreadyExistException extends BizException {
	public UsernameAlreadyExistException() {
		super("用户名已存在！");
	}
}
