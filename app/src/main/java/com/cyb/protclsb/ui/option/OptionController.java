package com.cyb.protclsb.ui.option;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.cyb.protclsb.LoginActivity;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.common.TipsEnum;
import com.cyb.protclsb.util.HttpUtil;
import com.cyb.protclsb.util.SharedHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OptionController extends Fragment {

    private static  String TAG = OptionController.class.getCanonicalName();
    private JSONObject requestPostMap = new JSONObject();
    private Map<String, String> paramsMap;
    private Context context;

    public OptionController(Context context) {
        this.context = context;
    }

    /**
     * 用户登出处理
     */
    public void logout() {

        SharedHelper sharedHelper = new SharedHelper(context);
        Map<String, String> loginMap = sharedHelper.readLogin();
        if(null != loginMap && loginMap.size() > 0){
            paramsMap = new HashMap<String, String>(1);
            paramsMap.put("authToken", loginMap.get("authToken"));
        }
        Thread thread = new Thread(logoutPostRun);
        thread.start();
        while (true){

            try {
                if(!thread.isAlive()){
                    if(null != requestPostMap){
                        if(!requestPostMap.isNull("validate")){

                            //登出成功，跳转到登录页
                            if(requestPostMap.getString("validate").equals("true")){

                                Intent intent=new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(context, TipsEnum.NOT_LOGIN.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }else if(!requestPostMap.isNull("r")){
                            Toast.makeText(context, requestPostMap.getString("r"), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context, TipsEnum.NOT_LOGIN.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }
                    return;
                }
                Thread.sleep(500);
            } catch (InterruptedException | JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, TipsEnum.SERVER_ERROR.getMsg(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * post请求线程
     */
    Runnable logoutPostRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, RequestConstant.LOGOUT_URL, paramsMap);
        }
    };
}