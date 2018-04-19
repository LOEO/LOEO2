package com.loeo.base.exception;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017/06/14 22:45
 * @version ：2018 Version：1.0
 */
public class UsernameAlreadyExistException extends BizException {
	public UsernameAlreadyExistException() {
		super("用户名已存在！");
	}
}
