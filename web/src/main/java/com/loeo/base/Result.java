package com.loeo.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author LT(286269159 @ qq.com)
 */
public class Result implements Serializable {
	private Boolean success;
	private String msg;
	private Object data;

	private Result() {

	}

	public static Result success(String msg) {
		return new ResultBuilder().setMsg(msg).setSuccess(true).build();
	}

	public static Result success() {
		return new ResultBuilder().setSuccess(true).build();
	}

	public static Result success(Object data) {
		return new ResultBuilder().setSuccess(true).setData(data).build();
	}

	public static Result failed(String msg) {
		return new ResultBuilder().setSuccess(false).setMsg(msg).build();
	}

	public static Result failed(BindingResult bindingResult) {
		List<ObjectError> errorList = bindingResult.getAllErrors();
		StringBuilder errorMsg = new StringBuilder();
		for (ObjectError objectError : errorList) {
			errorMsg.append(objectError.getDefaultMessage()).append(";");
		}
		return Result.failed(errorMsg.toString());
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	private static class ResultBuilder {
		private Result result = new Result();

		public Result build() {
			return result;
		}

		public ResultBuilder setMsg(String msg) {
			result.setMsg(msg);
			return this;
		}

		public ResultBuilder setData(Object data) {
			result.setData(data);
			return this;
		}

		public ResultBuilder setSuccess(Boolean success) {
			result.setSuccess(success);
			return this;
		}

	}
}
