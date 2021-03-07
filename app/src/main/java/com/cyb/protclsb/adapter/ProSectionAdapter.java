package com.cyb.protclsb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.cyb.protclsb.R;
import com.cyb.protclsb.mode.MySectionHeader;
import com.cyb.protclsb.mode.MySectionItem;
import com.qmuiteam.qmui.span.QMUIMarginImageSpan;
import com.qmuiteam.qmui.span.QMUITextSizeSpan;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.textview.QMUILinkTextView;

/**
 * ProQDGridSectionAdapter
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class ProSectionAdapter extends QMUIDefaultStickySectionAdapter<MySectionHeader, MySectionItem> {

    private DisplayMetrics displayMetrics;
    private LayoutInflater inflater;
    private Context context;

    private int outerPaddingLeft = 40;
    private int outerPaddingTop = 40;
    private int outerPaddingRight = 40;
    private int outerPaddingBottom = 40;

    public ProSectionAdapter(Context context, DisplayMetrics displayMetrics){
        this.inflater = LayoutInflater.from(context);
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
            setViewSize(containerView, size);
            setViewSize(containerView.getChildAt(0), size);

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

        LinearLayout layout = (LinearLayout) holder.itemView;
        layout.setBackgroundColor(Color.RED);

        //设置子元素独占一行
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(outerPaddingLeft + 20, 30, outerPaddingRight + 20,30);

        //QMUITextSizeSpan
        //设置项目名称标题
        LinearLayout titleLayout = new LinearLayout(context);
        TextView proNameTitleView = createTitleView("项目名称：");
        titleLayout.addView(proNameTitleView);
        TextView proNameContentView = createContentView("测试项目名称", false);
        proNameContentView.setLeft(0);
        titleLayout.addView(proNameContentView);
        layout.addView(titleLayout);

        //设置项目介绍
        LinearLayout contentLayout = new LinearLayout(context);
        TextView proIntroduceView = createTitleView("项目介绍：");
        contentLayout.addView(proIntroduceView);
        TextView proIntroduceContentView = createContentView("这是测试内容试内容这是测试内容试内容这是测试内容试内容这是测试内容试内容", true);
        contentLayout.addView(proIntroduceContentView);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(contentLayout);

        //设置项目运行截图
        LinearLayout imgLayout = new LinearLayout(context);
        imgLayout.setBackgroundColor(Color.RED);
        ImageView imageView = new ImageView(context);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        imageView.setImageResource(R.drawable.t_pro);
        imageView.setBackgroundColor(Color.BLUE);
        imgLayout.addView(imageView);
        imgLayout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(imgLayout);
    }

    @Override
    protected void onBindSectionLoadingItem(ViewHolder holder, int position, QMUISection<MySectionHeader, MySectionItem> section, boolean loadingBefore) {
        super.onBindSectionLoadingItem(holder, position, section, loadingBefore);
    }

    private TextView createTitleView(String character){

        TextView titleView = new TextView(context);
        titleView.setText(character);
        titleView.setTextSize(20);
        titleView.setPadding(outerPaddingLeft + 40, 0, 0,20);
        ViewGroup.LayoutParams descLayoutParams = titleView.getLayoutParams();
        if(null != descLayoutParams){
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
    private TextView createContentView(String character,boolean isIndent){

        if(isIndent){
            character = "\u3000\u3000" + character;
        }
        TextView titleView = new TextView(context);
        titleView.setText(character);
        titleView.setTextSize(16);
        titleView.setPadding(outerPaddingLeft + 40, 0, 0,20);
        ViewGroup.LayoutParams descLayoutParams = titleView.getLayoutParams();
        if(null != descLayoutParams){
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
    private void setViewSize(View view, int[] size){

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
}
