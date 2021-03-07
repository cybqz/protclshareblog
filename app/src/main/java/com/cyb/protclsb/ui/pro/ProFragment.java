package com.cyb.protclsb.ui.pro;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyb.protclsb.MainActivity;
import com.cyb.protclsb.adapter.ProSectionAdapter;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.mode.MySectionHeader;
import com.cyb.protclsb.mode.MySectionItem;
import com.cyb.protclsb.ui.login.LoginFragment;
import com.cyb.protclsb.util.HttpUtil;
import com.cyb.protclsb.R;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;
import org.json.JSONObject;

import java.util.*;

/**
 * ProFragment
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class ProFragment extends Fragment {

    private static String TAG = MainActivity.class.getCanonicalName();
    private String fragmentName = "ProFragment";

    private LoginFragment loginFragment;
    private JSONObject requestPostMap = new JSONObject();

    private View root;
    private QMUIGroupListView listView;
    private DisplayMetrics displayMetrics;
    private RecyclerView.LayoutManager mLayoutManager;
    private QMUIStickySectionLayout stickySectionLayout;
    private ProSectionAdapter qdGridSectionAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        try {
            displayMetrics = this.getResources().getDisplayMetrics();
            root = inflater.inflate(R.layout.fragment_pro, container, false);
            listView = root.findViewById(R.id.pro_section_list);

            renderProItemList(6);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("----------------------:" + fragmentName);
        return root;
    }

    private void renderProItemList(int count){

        for(int i = 1; i <= count; i++){

            qdGridSectionAdapter = new ProSectionAdapter(getContext(), displayMetrics);
            qdGridSectionAdapter.setData(Arrays.asList(createSection("Header-" + i, true)));

            mLayoutManager = createLayoutManager();
            stickySectionLayout = new QMUIStickySectionLayout(getContext());
            stickySectionLayout.setAdapter(qdGridSectionAdapter, true);
            stickySectionLayout.setLayoutManager(mLayoutManager);
            listView.addView(stickySectionLayout);
        }
    }

    private void demo(){

        QMUICommonListItemView  normalItem = listView.createItemView("Item 1");
        normalItem.setOrientation(QMUICommonListItemView.VERTICAL); //默认文字在左边

        QMUICommonListItemView itemWithDetail = listView.createItemView("Item 2");
        itemWithDetail.setDetailText("在右方的详细信息");//默认文字在左边   描述文字在右边

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

    private QMUISection<MySectionHeader, MySectionItem> createSection(String headerText, boolean isFold) {
        MySectionHeader header = new MySectionHeader(headerText);
        ArrayList<MySectionItem> contents = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            contents.add(new MySectionItem("item " + i));
        }
        QMUISection<MySectionHeader, MySectionItem> section = new QMUISection<>(header, contents, isFold);
        //是否需要加载更多
        section.setExistAfterDataToLoad(true);
        section.setExistBeforeDataToLoad(true);
        return section;
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }

    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, RequestConstant.LOGINED_USER_URL, null);
        }
    };

}