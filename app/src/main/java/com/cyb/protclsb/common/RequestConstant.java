package com.cyb.protclsb.common;

public class RequestConstant {


    private static final String BASE_URL = "http://192.168.1.33:9099/";

    public static final String LOGIN_URL = BASE_URL + "login/login";

    public static final String LOGOUT_URL = BASE_URL + "login/logout";

    public static final String LOGINED_USER_URL = BASE_URL + "user/getUser";

    public static final String DEFAULT_USER_IMAGE_BOY = BASE_URL + "image/headportrait/boy.png";
}
