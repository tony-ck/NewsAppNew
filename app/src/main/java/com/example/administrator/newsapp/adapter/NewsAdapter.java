package com.example.administrator.newsapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newsapp.R;
import com.example.administrator.newsapp.obj.Rows;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/14.
 */

public class NewsAdapter extends BaseListAdapter<Rows>{

    public NewsAdapter(Context context, ArrayList<Rows> list) {
        super(context, list, NO_DEFAULT);
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View v=view;
        //如果缓存convertView为空，则需要创建View
        if(v==null){
            viewHolder=new ViewHolder();
            v=mInflater.inflate(R.layout.item_news,null);
            viewHolder.iv= (ImageView)v.findViewById(R.id.iv_pic);
            viewHolder.tv_title=(TextView)v.findViewById(R.id.tv_title);
            viewHolder.tv_info=(TextView)v.findViewById(R.id.tv_info);
            v.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) v.getTag();
        }
        if(TextUtils.isEmpty(mList.get(i).getTitle())){
            viewHolder.tv_title.setText("暂无title数据");
        }else{
            viewHolder.tv_title.setText(mList.get(i).getTitle());
        }
        if(TextUtils.isEmpty(mList.get(i).getDescription())){
            viewHolder.tv_info.setText("暂无描述数据");
        }else{
            viewHolder.tv_info.setText(mList.get(i).getDescription());
        }
        displayImage(viewHolder.iv,mList.get(i).getImageHref());
        return v;
    }
    private class ViewHolder{
        public ImageView iv;
        public TextView tv_title,tv_info;
    }
}
