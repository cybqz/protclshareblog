package com.cyb.protclsb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.cyb.protclsb.R;

import java.util.List;
import java.util.Map;

public class ForumUserTalkListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Map<String,String>> list;
    private Context context;


    public ForumUserTalkListAdapter(Context context , List<Map<String,String>> list){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {

            view = mInflater.inflate(R.layout.fragment_forum_user_talk_list, null);

            viewHolder = new ViewHolder();
            viewHolder.leftLayout = view.findViewById(R.id.line_forum_user_talk_recive);
            viewHolder.leftMsg = view.findViewById(R.id.text_forum_user_talk_recive);
            viewHolder.leftPic = view.findViewById(R.id.img_forum_user_talk_recive);

            viewHolder.rightLayout = view.findViewById(R.id.line_forum_user_talk_send);
            viewHolder.rightMsg = view.findViewById(R.id.text_forum_user_talk_send);
            viewHolder.rightPic = view.findViewById(R.id.img_forum_user_talk_send);
            view.setTag(viewHolder);
        }else{

            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Map<String, String> msgMap = list.get(position);
        if(msgMap.get("type").equals("0")){
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msgMap.get("msg"));
            Glide.with(context).load(msgMap.get("pic")).into(viewHolder.leftPic);
        }else{
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msgMap.get("msg"));
            Glide.with(context).load(msgMap.get("pic")).into(viewHolder.rightPic);
        }
        return view;
    }
    public final class ViewHolder{

        public TextView leftMsg;
        public ImageView leftPic;
        public LinearLayout leftLayout;
        public TextView rightMsg;
        public ImageView rightPic;
        public LinearLayout rightLayout;
    }
}
