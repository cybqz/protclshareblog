package com.cyb.blogserver.common;

public class Tips {

	private String msg;
	
	private boolean show = true;
	
	private boolean validate;
	
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
