package com.cyb.protclsb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.cyb.protclsb.R;
import com.cyb.protclsb.mode.MySectionHeader;
import com.cyb.protclsb.mode.MySectionItem;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

/**
 * ProQDGridSectionAdapter
 *
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class ProQDGridSectionAdapter extends QMUIDefaultStickySectionAdapter<MySectionHeader, MySectionItem> {

    private LayoutInflater mInflater;
    private Context context;
    private TextView textViewHeader;
    private ImageView imgViewHeader;
    private TextView textViewItem;

    public ProQDGridSectionAdapter(Context context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    protected QMUIStickySectionAdapter.ViewHolder onCreateSectionHeaderViewHolder(@NonNull ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_pro_section_header, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @NonNull
    @Override
    protected QMUIStickySectionAdapter.ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {

        Context context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_pro_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindSectionHeader(final QMUIStickySectionAdapter.ViewHolder holder, final int position, QMUISection<MySectionHeader, MySectionItem> section) {
        textViewHeader = holder.itemView.findViewById(R.id.pro_section_layout_header_title);
        textViewHeader.setText(section.getHeader().getText());
        //点击事件
        imgViewHeader = holder.itemView.findViewById(R.id.pro_section_layout_header_img);
        if(null != imgViewHeader){
            imgViewHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.isForStickyHeader ? position : holder.getAdapterPosition();
                    toggleFold(pos, false);
                }
            });
        }
    }

    @Override
    protected void onBindSectionItem(QMUIStickySectionAdapter.ViewHolder holder, int position, QMUISection<MySectionHeader, MySectionItem> section, int itemIndex) {

        //获取ID并设置值
        textViewItem = holder.itemView.findViewById(R.id.pro_section_layout_list_text);
        textViewItem.setText(section.getItemAt(itemIndex).getText());
    }

    @Override
    protected void onBindSectionLoadingItem(QMUIStickySectionAdapter.ViewHolder holder, int position, QMUISection<MySectionHeader, MySectionItem> section, boolean loadingBefore) {
        super.onBindSectionLoadingItem(holder, position, section, loadingBefore);
    }
}
