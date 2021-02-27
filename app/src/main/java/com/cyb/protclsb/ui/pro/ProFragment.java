package com.cyb.protclsb.ui.pro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyb.protclsb.MainActivity;
import com.cyb.protclsb.adapter.ProQDGridSectionAdapter;
import com.cyb.protclsb.common.RequestConstant;
import com.cyb.protclsb.mode.MySectionHeader;
import com.cyb.protclsb.mode.MySectionItem;
import com.cyb.protclsb.ui.login.LoginFragment;
import com.cyb.protclsb.util.HttpUtil;
import com.cyb.protclsb.R;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
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

    private QMUIStickySectionLayout stickySectionLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProQDGridSectionAdapter qdGridSectionAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_pro, container, false);
        stickySectionLayout = root.findViewById(R.id.pro_section_layout);

        qdGridSectionAdapter = new ProQDGridSectionAdapter(getContext());
        qdGridSectionAdapter.setData(Arrays.asList(
                createSection("Header-1", false),
                createSection("Header-2", true),
                createSection("Header-3", true)));

        mLayoutManager = createLayoutManager();
        stickySectionLayout.setLayoutManager(mLayoutManager);
        stickySectionLayout.setAdapter(qdGridSectionAdapter, true);
        stickySectionLayout.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fragment_fade_enter));

        initAdapterLogicalOperation();

        System.out.println("----------------------:" + fragmentName);
        return root;
    }

    private void initAdapterLogicalOperation() {

        qdGridSectionAdapter.setCallback(new QMUIStickySectionAdapter.Callback<MySectionHeader, MySectionItem>() {

            @Override
            public void loadMore(final QMUISection<MySectionHeader, MySectionItem> section, final boolean loadMoreBefore) {
            }

            //点击事件
            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                if (holder.getItemViewType() != 1) {
                    int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
                    qdGridSectionAdapter.toggleFold(pos, false);
                }
            }

            //长按事件
            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int FirstPos = qdGridSectionAdapter.getSectionIndex(position);
                int SecondPos = qdGridSectionAdapter.getItemIndex(position);
                return true;
            }
        });
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
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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