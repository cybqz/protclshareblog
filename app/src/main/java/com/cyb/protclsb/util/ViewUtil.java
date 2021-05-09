package com.cyb.protclsb.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyb.protclsb.adapter.ProBannerImageAdapter;
import com.cyb.protclsb.mode.ProBannerImageBean;
import com.youth.banner.Banner;
import java.util.Arrays;
import java.util.List;

/**
 * ViewUtil
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/5/9
 */
public class ViewUtil {

    public static void initProImgBanner(Context context, Banner banner){

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        List<ProBannerImageBean> proBannerImageBeanList = Arrays.asList(
                new ProBannerImageBean("https://img2.baidu.com/it/u=294937280,3894744759&fm=26&fmt=auto&gp=0.jpg"),
                new ProBannerImageBean("https://img1.baidu.com/it/u=2476231256,1469186843&fm=26&fmt=auto&gp=0.jpg"));

        ProBannerImageAdapter proBannerImageAdapter = new ProBannerImageAdapter(proBannerImageBeanList,context);
        banner.setAdapter(proBannerImageAdapter);
        banner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
    }

    /**
     * 获取项目ID
     * @param view
     * @return
     */
    public static String getProIdFromRootView(View view){
        try {
            ViewGroup group = (ViewGroup) view;
            ViewGroup childGroup = (ViewGroup) group.getChildAt(0);
            String proId = (String) childGroup.getChildAt(0).getTag();
            return proId;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author 陈迎博
     * @Title 创建标题视图
     * @Description
     * @Date 2021/3/14
     */
    public static TextView createTitleView(Context context, DisplayMetrics displayMetrics, String character, int[] padding){

        TextView titleView = new TextView(context);
        titleView.setText(character);
        titleView.setTextSize(20);
        if(null != padding && padding.length > 0){
            titleView.setPadding(padding[0], padding[1], padding[2],padding[3]);
        }
        ViewGroup.LayoutParams descLayoutParams = titleView.getLayoutParams();
        if(null != descLayoutParams && null != displayMetrics){
            descLayoutParams.width = displayMetrics.widthPixels - 50;
            descLayoutParams.height = 100;
        }
        return titleView;
    }

    /**
     * @Author 陈迎博
     * @Description
     * @Date 2021/3/7
     * @param character 内容
     * @param isIndent 是否首行缩进
     */
    public static TextView createContentView(Context context, DisplayMetrics displayMetrics, int outerPaddingLeft, String character,boolean isIndent, boolean isSetPadding){

        if(isIndent){
            character = "\u3000\u3000" + character;
        }
        TextView titleView = new TextView(context);
        titleView.setText(character);
        titleView.setTextSize(16);
        if(isSetPadding){
            titleView.setPadding(outerPaddingLeft + 40, 0, 0,20);
        }
        ViewGroup.LayoutParams descLayoutParams = titleView.getLayoutParams();
        if(null != descLayoutParams && null != displayMetrics){
            descLayoutParams.width = displayMetrics.widthPixels - 50;
            descLayoutParams.height = 100;
        }
        return titleView;
    }

    /**
     * @Author 陈迎博
     * @Title 设置View宽度、高度
     * @Description
     * @Date 2021/3/7
     */
    public static void setViewSize(View view, int[] size){

        if(null != view){
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if(null != layoutParams){
                layoutParams.width = size[0];
                layoutParams.height = size[1];
                view.setLayoutParams(layoutParams);
            }else {
                view.setLayoutParams(new LinearLayout.LayoutParams(size[0],size[1]));
            }
        }
    }

    /**
     * @Author 陈迎博
     * @Title 设置View边距
     * @Description
     * @Date 2021/3/9
     */
    public static void setViewMargins(View view, int[] margins){
        if(null !=view){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(margins[0],margins[1],margins[2],margins[3]);
            view.setLayoutParams(layoutParams);
        }
    }
}
