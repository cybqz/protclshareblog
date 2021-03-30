package com.cyb.protclsb.util;

import android.app.Fragment;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;

/**
 * Toast辅助类
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/3/30
 */
public class ToastUtil {

    public static void showToastShort(FragmentActivity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
