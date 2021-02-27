package com.cyb.protclsb.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    public final static String DEFAULT = "请先登陆再使用";
    public final static String SEND_START = "开始发送请求";
    public final static String SEND_SUCCESS = "发送请求成功";
    public final static String LOGIN_FAILD = "登陆失败";
    public final static String LOGIN_SUCCESS = "登陆成功";

    private MutableLiveData<String> mText;

    public LoginViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText(String value) {
        mText.setValue(value);
        return mText;
    }

}