package com.loeo.base.exception;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-04-20 11:27:27
 */
public class IsNotLeafNodeCanNotDelete extends BizException{
	public IsNotLeafNodeCanNotDelete() {
		super("非叶子节点不能删除！");
	}
}
