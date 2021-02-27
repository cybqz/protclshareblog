package com.cyb.protclsb.common;

public enum TipsEnum {

    SELECT_ERROR(400,"选择错误"),
    NOT_LOGIN(400, "未登录"),
    SERVER_ERROR(500, "服务器错误");

    private int code;
    private String msg;

    private TipsEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
