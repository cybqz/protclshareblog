package com.cyb.protclsb.ui.pro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.cyb.protclsb.MainActivity;
import com.cyb.protclsb.R;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.util.HttpUtil;
import com.cyb.protclsb.util.ToastUtil;
import com.cyb.protclsb.util.ViewUtil;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.youth.banner.Banner;
import org.json.JSONObject;

/**
 * ProDetailFragment
 *
 * @Author 陈迎博
 * @Description 项目详情
 * @Date 2021/2/27
 */
public class ProDetailFragment extends Fragment {

    private static String TAG = MainActivity.class.getCanonicalName();

    private View root;
    private String proId;
    private QMUIGroupListView listView;
    private JSONObject requestPostMap = new JSONObject();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        try {
            root = inflater.inflate(R.layout.fragment_pro_detail, container, false);
            setRootView();

            listView = root.findViewById(R.id.pro_detail_list);
            if(null != getArguments()){
                proId = getArguments().getString("proId");
                //根据proId查询项目完整信息
                getProInfoData();
                //渲染页面
                render();
                ToastUtil.showToastShort(getActivity(),"Render success for" + proId);
            }else {
                ToastUtil.showToastShort(getActivity(),"Arguments is null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
    }

    /**
     * 根据proId查询项目完整信息
     *
     * @Author 陈迎博
     * @Title 根据proId查询项目完整信息
     * @Description
     * @Date 2021/4/17
     */
    private void getProInfoData() {
    }

    private void setRootView(){
        try {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 20, 0, 300);
            root.setLayoutParams(layoutParams);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void render(){

        LinearLayout layout = new LinearLayout(getContext());
        layout.setBackgroundColor(Color.GRAY);
        //设置子元素独占一行
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 20, 0,40);
        layout.setTag(proId);
        ViewUtil.setViewMargins(layout, new int[]{0,20,0,20});

        //父布局会优先子布局获取焦点
        layout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        //设置项目名称标题
        LinearLayout titleLayout = new LinearLayout(getContext());
        ViewUtil.setViewMargins(titleLayout, new int[]{0,0,0,0});
        titleLayout.setClickable(false);

        TextView proNameTitleView = ViewUtil.createTitleView(getContext(), null, "项目名称：", new int[]{60, 0, 0,20});

        //设置项目ID
        proNameTitleView.setTag(proId);
        titleLayout.addView(proNameTitleView);
        TextView proNameContentView = ViewUtil.createContentView(getContext(), null, 0, "这是项目名称", false,true);
        proNameContentView.setPadding(0,0,0,0);
        ViewUtil.setViewMargins(proNameContentView, new int[]{0,0,0,0});
        titleLayout.addView(proNameContentView);
        layout.addView(titleLayout);

        //设置项目介绍
        LinearLayout contentLayout = new LinearLayout(getContext());
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        ViewUtil.setViewMargins(contentLayout, new int[]{60,10,60,10});
        contentLayout.setClickable(false);

        TextView proIntroduceTitleView = ViewUtil.createTitleView(getContext(), null, "项目简介：", null);
        contentLayout.addView(proIntroduceTitleView);
        TextView proIntroduceContentView = ViewUtil.createContentView(getContext(), null, 0, "这是项目简介", true, false);
        contentLayout.addView(proIntroduceContentView);
        layout.addView(contentLayout);

        //设置项目运行截图
        LinearLayout imgLayout = new LinearLayout(getContext());
        imgLayout.setOrientation(LinearLayout.VERTICAL);
        ViewUtil.setViewMargins(imgLayout, new int[]{60,10,60,10});
        imgLayout.setClickable(false);

        TextView proRunPicTitleView = ViewUtil.createTitleView(getContext(), null, "运行截图：", new int[]{0,0,0,10});
        imgLayout.addView(proRunPicTitleView);

        Banner banner = new Banner(imgLayout.getContext());
        ViewUtil.initProImgBanner(getContext(), banner);
        imgLayout.addView(banner);
        layout.addView(imgLayout);

        listView.addView(layout);
    }

    Runnable postRun = new Runnable() {
        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, RequestConstant.LOGINED_USER_URL, null);
        }
    };
}