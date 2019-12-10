package com.cyb.blogserver.common;

public class Tips {

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 页面是否提示
	 */
	private boolean show = true;

	/**
	 * 验证结果
	 */
	private boolean validate;

	/**
	 * 返回数据
	 */
	private Object data;
	
	public Tips(String msg, boolean validate) {
		super();
		this.msg = msg;
		this.validate = validate;
	}
	
	public Tips(String msg, boolean validate, Object data) {
		super();
		this.msg = msg;
		this.data = data;
		this.validate = validate;
	}
	
	public Tips(String msg, boolean show, boolean validate) {
		super();
		this.msg = msg;
		this.show = show;
		this.validate = validate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
