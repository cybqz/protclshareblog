package com.cyb.protclsb.ui.pro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyb.protclsb.MainActivity;
import com.cyb.protclsb.R;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.util.HttpUtil;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;
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
            }

            demo();
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
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

    private void demo(){

        QMUICommonListItemView  normalItem = listView.createItemView("Item 1");
        normalItem.setOrientation(QMUICommonListItemView.VERTICAL); //默认文字在左边

        QMUICommonListItemView itemWithDetail = listView.createItemView("Item 2");
        itemWithDetail.setDetailText(proId);//默认文字在左边   描述文字在右边

        QMUICommonListItemView itemWithDetailBelow = listView.createItemView("Item 3");
        itemWithDetailBelow.setOrientation(QMUICommonListItemView.VERTICAL);
        itemWithDetailBelow.setDetailText("在标题下方的详细信息");//默认文字在左边   描述文字在标题下边

        QMUICommonListItemView itemWithChevron = listView.createItemView("Item 4");
        ////默认文字在左边   右侧更多按钮
        itemWithChevron.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView itemWithSwitch = listView.createItemView("Item 5");
        itemWithSwitch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        itemWithSwitch.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(), "checked = " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });//默认文字在左边   右侧选择按钮
        QMUICommonListItemView itemWithCustom = listView.createItemView("Item 6");
        itemWithCustom.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        QMUILoadingView loadingView = new QMUILoadingView(getActivity());
        itemWithCustom.addAccessoryCustomView(loadingView);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    Toast.makeText(getActivity(), text + " is Clicked", Toast.LENGTH_SHORT).show();
                }
            }
        };//默认文字在左边   自定义加载框按钮

        QMUIGroupListView.newSection(getContext())
                .setTitle("Section 1: 默认提供的样式")
                .setDescription("Section 1 的描述")
                .addItemView(normalItem, onClickListener)
                .addItemView(itemWithDetail, onClickListener)
                .addItemView(itemWithDetailBelow, onClickListener)
                .addItemView(itemWithChevron, onClickListener)
                .addItemView(itemWithSwitch, onClickListener)
                .addTo(listView);

        QMUIGroupListView.newSection(getContext())
                .setTitle("Section 2: 自定义右侧 View")
                .addItemView(itemWithCustom, onClickListener)
                .addTo(listView);
    }

    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, RequestConstant.LOGINED_USER_URL, null);
        }
    };
}