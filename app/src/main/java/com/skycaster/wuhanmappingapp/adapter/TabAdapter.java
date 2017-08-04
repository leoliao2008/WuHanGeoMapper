package com.skycaster.wuhanmappingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseViewHolder;
import com.skycaster.wuhanmappingapp.bean.TabItem;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.ViewHolder> {

    private ArrayList<TabItem> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public TabAdapter(ArrayList<TabItem> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_tab,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TabItem item = mList.get(position);
        holder.iv_imageView.setImageResource(item.getImageSrc());
        holder.tv_title.setText(item.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(item,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener=listener;
    }

    public interface OnItemClickListener{
        void onItemClick(TabItem item,int position);
    }


    public class ViewHolder extends BaseViewHolder{
        private TextView tv_title;
        private ImageView iv_imageView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initChildViews() {
            tv_title= (TextView) findViewById(R.id.tv_tab_title);
            iv_imageView= (ImageView) findViewById(R.id.iv_tab_icon);
        }
    }

}
