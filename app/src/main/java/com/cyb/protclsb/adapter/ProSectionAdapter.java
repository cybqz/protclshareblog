package com.cyb.protclsb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import com.cyb.protclsb.listener.ProClickListener;
import com.cyb.protclsb.mode.MySectionHeader;
import com.cyb.protclsb.mode.MySectionItem;
import com.cyb.protclsb.util.ViewUtil;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.youth.banner.Banner;

/**
 * ProSectionAdapter
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class ProSectionAdapter extends QMUIDefaultStickySectionAdapter<MySectionHeader, MySectionItem> {

    private ProClickListener proClickListener;
    private DisplayMetrics displayMetrics;
    private LayoutInflater inflater;
    private Context context;

    private int outerPaddingLeft = 40;
    private int outerPaddingTop = 40;
    private int outerPaddingRight = 40;
    private int outerPaddingBottom = 40;

    public ProSectionAdapter(Context context, DisplayMetrics displayMetrics, ProClickListener proClickListener){
        this.inflater = LayoutInflater.from(context);
        this.proClickListener = proClickListener;
        this.displayMetrics = displayMetrics;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateSectionHeaderViewHolder(@NonNull ViewGroup viewGroup) {
        return new ViewHolder(new QMUICommonListItemView(context));
    }

    @NonNull
    @Override
    protected ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {
        return new ViewHolder(new LinearLayout(context));
    }

    @Override
    protected void onBindSectionHeader(final ViewHolder holder, final int position, QMUISection<MySectionHeader, MySectionItem> section) {

        MySectionHeader sectionHeader = section.getHeader();
        QMUICommonListItemView view = (QMUICommonListItemView) holder.itemView;
        view.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        view.setText(sectionHeader.getText());
        view.setPadding(outerPaddingLeft,outerPaddingTop,outerPaddingRight,outerPaddingBottom);

        TextView textView = view.getTextView();
        textView.setTextSize(20);

        //获取图标元素并设置
        ViewGroup containerView = view.getAccessoryContainerView();
        if(null != containerView){

            int [] size = new int[]{80, 80};

            //获取右侧图标元素，设置大小
            ViewUtil.setViewSize(containerView, size);
            ViewUtil.setViewSize(containerView.getChildAt(0), size);

            //设置方向，添加单机事件
            containerView.setRotation(section.isFold()?0f:90f);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
                    toggleFold(pos, false);
                }
            });
        }
    }

    @Override
    protected void onBindSectionItem(ViewHolder holder, int position, QMUISection<MySectionHeader, MySectionItem> section, int itemIndex) {

        LinearLayout linearLayoutRoot = (LinearLayout) holder.itemView;
        String proId = (String) linearLayoutRoot.getTag();
        MySectionItem sectionItem = section.getItemAt(itemIndex);

        /**
         * 如果已渲染，则直接返回
         */
        if(sectionItem.getId().equals(proId)){
            return;
        }
        linearLayoutRoot.setTag(sectionItem.getId());
        linearLayoutRoot.setBackgroundColor(Color.RED);

        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(Color.GRAY);
        //设置子元素独占一行
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 20, 0,40);
        layout.setTag(sectionItem.getId());
        ViewUtil.setViewMargins(layout, new int[]{0,20,0,20});

        //父布局会优先子布局获取焦点
        layout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        //添加单机事件
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proId = ViewUtil.getProIdFromRootView(v);
                proClickListener.onClickListener(proId);
            }
        });

        //设置项目名称标题
        LinearLayout titleLayout = new LinearLayout(context);
        ViewUtil.setViewMargins(titleLayout, new int[]{0,0,0,0});
        titleLayout.setClickable(false);

        TextView proNameTitleView = ViewUtil.createTitleView(context, displayMetrics,"项目名称：", new int[]{60, 0, 0,20});

        //设置项目ID
        proNameTitleView.setTag(sectionItem.getId());
        titleLayout.addView(proNameTitleView);
        TextView proNameContentView = ViewUtil.createContentView(context, displayMetrics, outerPaddingLeft, sectionItem.getName(), false,true);
        proNameContentView.setPadding(0,0,0,0);
        ViewUtil.setViewMargins(proNameContentView, new int[]{0,0,0,0});
        titleLayout.addView(proNameContentView);
        layout.addView(titleLayout);

        //设置项目介绍
        LinearLayout contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        ViewUtil.setViewMargins(contentLayout, new int[]{60,10,60,10});
        contentLayout.setClickable(false);

        TextView proIntroduceTitleView = ViewUtil.createTitleView(context, displayMetrics, "项目简介：", null);
        contentLayout.addView(proIntroduceTitleView);
        TextView proIntroduceContentView = ViewUtil.createContentView(context, displayMetrics, outerPaddingLeft, sectionItem.getIntroduce(), true, false);
        contentLayout.addView(proIntroduceContentView);
        layout.addView(contentLayout);

        //设置项目运行截图
        LinearLayout imgLayout = new LinearLayout(context);
        imgLayout.setOrientation(LinearLayout.VERTICAL);
        ViewUtil.setViewMargins(imgLayout, new int[]{60,10,60,10});
        imgLayout.setClickable(false);

        TextView proRunPicTitleView = ViewUtil.createTitleView(context,displayMetrics, "运行截图：", new int[]{0,0,0,10});
        imgLayout.addView(proRunPicTitleView);

        Banner banner = new Banner(imgLayout.getContext());
        ViewUtil.initProImgBanner(context, banner);
        imgLayout.addView(banner);
        layout.addView(imgLayout);

        linearLayoutRoot.addView(layout);
    }

    @Override
    protected void onBindSectionLoadingItem(ViewHolder holder, int position, QMUISection<MySectionHeader, MySectionItem> section, boolean loadingBefore) {
        super.onBindSectionLoadingItem(holder, position, section, loadingBefore);
    }
}
