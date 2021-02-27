package com.cyb.protclsb.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.cyb.protclsb.ui.login.LoginViewModel;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SharedHelper {

    private Context context;

    private SharedHelper() {
    }

    public SharedHelper(Context context) {
        this.context = context;
    }


    //定义一个保存数据的方法
    public void saveLogin(JSONObject jsonObject) {
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", data.getString("userName"));
            editor.putString("authToken", data.getString("authToken"));
            editor.commit();
            Toast.makeText(context, LoginViewModel.LOGIN_SUCCESS, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, LoginViewModel.LOGIN_FAILD, Toast.LENGTH_SHORT).show();
        }
    }

    //定义一个读取SP文件的方法
    public Map<String, String> readLogin() {
        Map<String, String> data = null;
        if(null != context){

            SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
            String username = sp.getString("username", null);
            String authToken = sp.getString("authToken", null);
            if(null != username && null != authToken){
                data = new HashMap<String, String>();
                data.put("username", username);
                data.put("authToken", authToken);
            }
        }
        return data;
    }
}
