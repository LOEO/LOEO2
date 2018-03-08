package com.loeo.base.exception;

/**
 * Created by LOEO on 2017/06/14 22:45
 */
public class DuplicateUsernameException extends BizException {
	public DuplicateUsernameException() {
		super("存在重复的用户名！");
	}
}
