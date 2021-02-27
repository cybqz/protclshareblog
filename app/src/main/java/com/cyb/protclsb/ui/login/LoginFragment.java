package com.cyb.protclsb.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.protclsb.MainActivity;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.util.HttpUtil;
import com.cyb.protclsb.util.SharedHelper;
import com.cyb.protclsb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static  String TAG = LoginFragment.class.getCanonicalName();
    private JSONObject requestPostMap = new JSONObject();
    private EditText text_userName;
    private EditText text_password;
    private HashMap<String, String> paramsMap;
    private Button button;
    private TextView textLogin;
    private SharedHelper sharedHelper;
    private Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        textLogin = view.findViewById(R.id.text_login);
        button=view.findViewById(R.id.button_login);
        button.setOnClickListener(this);

        text_userName = view.findViewById(R.id.text_userName);
        text_password = view.findViewById(R.id.text_password);

        context = getActivity().getApplicationContext();

        sharedHelper = new SharedHelper(context);

        tip(LoginViewModel.DEFAULT);

        System.out.println("----------------------LoginFragment");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //登陆成功，跳转到主页
        Map<String, String> data = sharedHelper.readLogin();
        if(null != data){

            Intent intent=new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 单击-登陆
     * @param view
     */
    @Override
    public void onClick(View view) {

        if(null != text_userName && null != text_password){
            Log.e(TAG, text_userName.getText().toString() + "\t" + text_password.getText().toString());
            tip(LoginViewModel.SEND_START);
            paramsMap = new HashMap<String, String>(2);
            paramsMap.put("userName", text_userName.getText().toString());
            paramsMap.put("password", text_password.getText().toString());

            Thread thread = new Thread(postRun);
            thread.start();
            while (true){

                try {
                    if(!thread.isAlive()){
                        if(null != requestPostMap){

                            if(!requestPostMap.isNull("validate")){

                                //登陆成功，跳转到主页
                                if(requestPostMap.getString("validate").equals("true")){

                                    sharedHelper.saveLogin(requestPostMap);

                                    Intent intent=new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    tip(LoginViewModel.LOGIN_FAILD);
                                }
                            }else if(!requestPostMap.isNull("r")){
                                tip(requestPostMap.getString("r"));
                            }
                        }
                        return;
                    }
                    Thread.sleep(500);
                } catch (InterruptedException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * post请求线程
     */
    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, RequestConstant.LOGIN_URL, paramsMap);
        }
    };

    private void tip(String value){
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getText(value).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textLogin.setText(s);
            }
        });
    }


    /**
     * 文件下载
     *
     * @param fileUrl
     */
    private void downloadFile(String fileUrl) {
        try {
            // 新建一个URL对象
            URL url = new URL(fileUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("GET");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                String filePath = "";//下载文件保存在本地的地址
                File descFile = new File(filePath);
                FileOutputStream fos = new FileOutputStream(descFile);
                ;
                byte[] buffer = new byte[1024];
                int len;
                InputStream inputStream = urlConn.getInputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    // 写到本地
                    fos.write(buffer, 0, len);
                }
            } else {
                Log.e(TAG, "文件下载失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 文件上传
     *
     * @param filePath
     * @param paramsMap
     */
    private void upLoadFile(String filePath, HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "https://xxx.com/uploadFile";
            File file = new File(filePath);
            //新建url对象
            URL url = new URL(baseUrl);
            //通过HttpURLConnection对象,向网络地址发送请求
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            //设置该连接允许读取
            urlConn.setDoOutput(true);
            //设置该连接允许写入
            urlConn.setDoInput(true);
            //设置不能适用缓存
            urlConn.setUseCaches(false);
            //设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);   //设置连接超时时间
            //设置读取超时时间
            urlConn.setReadTimeout(5 * 1000);   //读取超时
            //设置连接方法post
            urlConn.setRequestMethod("POST");
            //设置维持长连接
            urlConn.setRequestProperty("connection", "Keep-Alive");
            //设置文件字符集
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            //设置文件类型
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + "*****");
            String name = file.getName();
            DataOutputStream requestStream = new DataOutputStream(urlConn.getOutputStream());
            requestStream.writeBytes("--" + "*****" + "\r\n");
            //发送文件参数信息
            StringBuilder tempParams = new StringBuilder();
            tempParams.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"; ");
            int pos = 0;
            int size = paramsMap.size();
            for (String key : paramsMap.keySet()) {
                tempParams.append(String.format("%s=\"%s\"", key, paramsMap.get(key), "utf-8"));
                if (pos < size - 1) {
                    tempParams.append("; ");
                }
                pos++;
            }
            tempParams.append("\r\n");
            tempParams.append("Content-Type: application/octet-stream\r\n");
            tempParams.append("\r\n");
            String params = tempParams.toString();
            requestStream.writeBytes(params);
            //发送文件数据
            FileInputStream fileInput = new FileInputStream(file);
            int bytesRead;
            byte[] buffer = new byte[1024];
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            while ((bytesRead = in.read(buffer)) != -1) {
                requestStream.write(buffer, 0, bytesRead);
            }
            requestStream.writeBytes("\r\n");
            requestStream.flush();
            requestStream.writeBytes("--" + "*****" + "--" + "\r\n");
            requestStream.flush();
            fileInput.close();
            int statusCode = urlConn.getResponseCode();
            if (statusCode == 200) {
                // 获取返回的数据
                JSONObject result = HttpUtil.streamToJSONObject(TAG, urlConn.getInputStream());
                Log.e(TAG, "上传成功，result--->" + result.toString());
            } else {
                Log.e(TAG, "上传失败");
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }
}