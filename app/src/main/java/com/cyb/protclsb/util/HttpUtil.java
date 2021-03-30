package com.cyb.protclsb.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.cyb.protclsb.common.TipsEnum;
import com.cyb.protclsb.ui.login.LoginViewModel;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 网络请求辅助类
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/3/30
 */
public class HttpUtil {

    /**
     * 加载网络图片
     *
     * @Author 陈迎博
     * @Title
     * @Description
     * @Date 2021/3/30
     */
    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL
                    .openConnection();
            // 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            // 连接设置获得数据流
            conn.setDoInput(true);
            // 不使用缓存
            conn.setUseCaches(false);
            // 这句可有可无，没有影响
            // conn.connect();
            // 得到数据流
            InputStream is = conn.getInputStream();
            // 解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            // 关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * post提交数据
     */
    public static JSONObject requestPost(String TAG, String requestURL, Map<String, String> paramsMap) {


        String msg = "";
        try {
            //合成参数
            String params = null;
            if(null != paramsMap && paramsMap.size() > 0){
                StringBuilder tempParams = new StringBuilder();
                int pos = 0;
                for (String key : paramsMap.keySet()) {
                    if (pos >0) {
                        tempParams.append("&");
                    }
                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                    pos++;
                }
                params = tempParams.toString();
            }

            Log.e(TAG,"request info " + requestURL + "\t" + params);
            msg = LoginViewModel.SEND_START;
            URL url = new URL(requestURL);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 开始连接
            urlConn.connect();

            // 发送请求参数
            PrintWriter dos = new PrintWriter(urlConn.getOutputStream());
            if(null != params){
                dos.write(params);
            }
            dos.flush();
            dos.close();
            // 判断请求是否成功
            int code = urlConn.getResponseCode();
            if (code == 200) {
                // 获取返回的数据
                JSONObject result = streamToJSONObject(TAG, urlConn.getInputStream());
                Log.e(TAG, "Post方式请求成功，result--->" + result);
                return result;

            } else {
                msg = String.valueOf(code);
                Log.e(TAG, "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            msg = TipsEnum.SERVER_ERROR.getMsg();
            Log.e(TAG, e.toString());
        }
        return setResult(msg);
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @param TAG
     * @return
     */
    public static JSONObject streamToJSONObject(String TAG, InputStream is) {
        JSONObject jsonObject = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            String s = new String(byteArray);
            if(null != s && !s.equals("")){
                jsonObject = new JSONObject(s);
            }
            return jsonObject;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    public static JSONObject setResult(String message){
        try {
            JSONObject result = new JSONObject();
            result.put("r", message);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
