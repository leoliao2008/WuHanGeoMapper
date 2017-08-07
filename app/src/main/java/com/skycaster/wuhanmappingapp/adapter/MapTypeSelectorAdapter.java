package com.skycaster.wuhanmappingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseViewHolder;
import com.skycaster.wuhanmappingapp.base.MyBaseAdapter;
import com.skycaster.wuhanmappingapp.bean.MapType;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/7.
 */

public class MapTypeSelectorAdapter extends MyBaseAdapter<MapType,MapTypeSelectorAdapter.ViewHolder> {


    public MapTypeSelectorAdapter(ArrayList<MapType> list, Context context) {
        super(list, context, R.layout.item_map_type_selector);
    }

    @Override
    protected void updateViewHolder(ViewHolder viewHolder, int position, MapType item, View convertView, ViewGroup parent) {
        viewHolder.tv_title.setText(item.getTitle());
        viewHolder.iv_icon.setImageResource(item.getDrawableSrc());

    }

    @Override
    protected ViewHolder instantiateViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }



    public class ViewHolder extends BaseViewHolder{
        private ImageView iv_icon;
        private TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initChildViews() {
            iv_icon= (ImageView) findViewById(R.id.spinner_item_iv_map_type_icon);
            tv_title= (TextView) findViewById(R.id.spinner_item_tv_map_type_title);

        }
    }
}
