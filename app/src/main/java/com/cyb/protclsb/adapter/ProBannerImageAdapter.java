package com.cyb.protclsb.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cyb.protclsb.R;
import com.cyb.protclsb.mode.ProBannerImageBean;
import com.cyb.protclsb.util.HttpUtil;
import com.youth.banner.adapter.BannerAdapter;
import java.util.List;

public class ProBannerImageAdapter extends BannerAdapter<ProBannerImageBean, ProBannerImageAdapter.BannerViewHolder> {

    private Context context;
    private List<ProBannerImageBean> dataList;

    public ProBannerImageAdapter(List<ProBannerImageBean> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setMaxHeight(400);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, ProBannerImageBean data, int position, int size) {

        Bitmap bitmap = HttpUtil.getHttpBitmap(data.getPath());
        if(null != bitmap){
            holder.imageView.setImageBitmap(bitmap);
        }else {
            holder.imageView.setBackgroundResource(R.drawable.t_pro);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
