package com.cyb.protclsb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
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
 * ProSectionAdapter
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

        LinearLayout linearLayoutRoot = (LinearLayout) holder.itemView;

        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(Color.GRAY);

        //设置子元素独占一行
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 20, 0,40);
        setViewMargins(layout, new int[]{0,20,0,20});

        //父布局会优先子布局获取焦点
        layout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        //添加单机事件
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup group = (ViewGroup) v;
                for(int i = 0; i < group.getChildCount(); i++){
                    ViewGroup childGroup = (ViewGroup) group.getChildAt(i);
                    String proId = (String) childGroup.getChildAt(0).getTag();
                    Toast.makeText(context, proId, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        //设置项目名称标题
        LinearLayout titleLayout = new LinearLayout(context);
        setViewMargins(titleLayout, new int[]{0,0,0,0});
        titleLayout.setClickable(false);

        TextView proNameTitleView = createTitleView("项目名称：", new int[]{60, 0, 0,20});

        //设置项目ID
        proNameTitleView.setTag("12345678");
        titleLayout.addView(proNameTitleView);
        TextView proNameContentView = createContentView("测试项目名称", false,true);
        proNameContentView.setPadding(0,0,0,0);
        setViewMargins(proNameContentView, new int[]{0,0,0,0});
        titleLayout.addView(proNameContentView);
        layout.addView(titleLayout);

        //设置项目介绍
        LinearLayout contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        setViewMargins(contentLayout, new int[]{60,10,60,10});
        contentLayout.setClickable(false);

        TextView proIntroduceTitleView = createTitleView("项目介绍：", null);
        contentLayout.addView(proIntroduceTitleView);
        TextView proIntroduceContentView = createContentView("这是测试内容试内容这是测试内容试内容这是测试内容试内容这是测试内容试内容", true, false);
        contentLayout.addView(proIntroduceContentView);
        layout.addView(contentLayout);

        //设置项目运行截图
        LinearLayout imgLayout = new LinearLayout(context);
        imgLayout.setOrientation(LinearLayout.VERTICAL);
        setViewMargins(imgLayout, new int[]{60,10,60,10});
        imgLayout.setClickable(false);

        TextView proRunPicTitleView = createTitleView("运行截图：", new int[]{0,0,0,10});
        imgLayout.addView(proRunPicTitleView);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
        imageView.setImageResource(R.drawable.t_pro);
        imageView.setBackgroundColor(Color.BLUE);
        imgLayout.addView(imageView);
        layout.addView(imgLayout);

        //渲染
        linearLayoutRoot.addView(layout);
    }

    @Override
    protected void onBindSectionLoadingItem(ViewHolder holder, int position, QMUISection<MySectionHeader, MySectionItem> section, boolean loadingBefore) {
        super.onBindSectionLoadingItem(holder, position, section, loadingBefore);
    }

    private TextView createTitleView(String character, int[] padding){

        TextView titleView = new TextView(context);
        titleView.setText(character);
        titleView.setTextSize(20);
        if(null != padding && padding.length > 0){
            titleView.setPadding(padding[0], padding[1], padding[2],padding[3]);
        }
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
    private TextView createContentView(String character,boolean isIndent, boolean isSetPadding){

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

    /**
     * @Author 陈迎博
     * @Title 设置View边距
     * @Description
     * @Date 2021/3/9
     */
    private void setViewMargins(View view, int[] margins){
        if(null !=view){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(margins[0],margins[1],margins[2],margins[3]);
            view.setLayoutParams(layoutParams);
        }
    }
}
