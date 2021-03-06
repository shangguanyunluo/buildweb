package com.lenovo.cloudbuild.util;

import java.io.Serializable;

public class ResponseStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 成功类型
	 */
	private static final String SUCCESS_TYPE = "SUCCESS";
	/**
	 * 失败类型
	 */
	private static final String FAIL_TYPE = "FAIL";
	/**
	 * 没有数据提示
	 */
	private static final String NO_DATA = "NO DATA";
	/**
	 * 成功信息
	 */
	private static final String SUCCESS_MSG = "REQUEST SUCCESS";
	/**
	 * 失败信息
	 */
	private static final String FAIL_MSG = "REQUEST FAIL";

	/**
	 * 类型
	 */
	private String type;
	/**
	 * 信息
	 */
	private String msg;
	/**
	 * 数据
	 */
	private Object data;

	private ResponseStatus() {
	}

	/**
	 * 重写toString
	 */
	@Override
	public String toString() {
		return "ApiResponse [type=" + type + ", msg=" + msg + ", data=" + data + "]";
	}

	/**
	 * 
	 * @Title: createSuccessResponse
	 * @Description: 成功返回,包含信息和数据
	 * @param msg
	 * @param data
	 * @return
	 */
	public static ResponseStatus createSuccessResponse(String msg, Object data) {
		ResponseStatus response = new ResponseStatus();
		response.setType(SUCCESS_TYPE);
		response.setMsg(msg);
		response.setData(data);
		return response;
	}

	/**
	 * 
	 * @Title: createSuccessResponse
	 * @Description: 成功返回,只包含信息
	 * @param msg
	 * @return
	 */
	public static ResponseStatus createSuccessResponse(String msg) {
		return createSuccessResponse(msg, NO_DATA);
	}

	/**
	 * 
	 * @Title: createSuccessResponse
	 * @Description: 成功返回,只包含数据
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static ResponseStatus createSuccessResponse(Object data, Class<?> clazz) {
		return createSuccessResponse(SUCCESS_MSG, data);
	}

	/**
	 * 
	 * @Title: createFailResponse
	 * @Description: 失败返回,包含信息和数据
	 * @param msg
	 * @param data
	 * @return
	 */
	public static ResponseStatus createFailResponse(String msg, Object data) {
		ResponseStatus response = new ResponseStatus();
		response.setType(FAIL_TYPE);
		response.setMsg(msg);
		response.setData(data);
		return response;
	}

	/**
	 * 
	 * @Title: createFailResponse
	 * @Description: 失败返回,只包含信息
	 * @param msg
	 * @return
	 */
	public static ResponseStatus createFailResponse(String msg) {
		return createFailResponse(msg, NO_DATA);
	}

	/**
	 * 
	 * @Title: createFailResponse
	 * @Description: 失败返回,只包含数据
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static ResponseStatus createFailResponse(Object data, Class<?> clazz) {
		return createFailResponse(FAIL_MSG, data);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
