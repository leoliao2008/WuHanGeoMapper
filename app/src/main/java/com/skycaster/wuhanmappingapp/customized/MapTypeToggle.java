package com.skycaster.wuhanmappingapp.customized;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.adapter.MapTypeTogglePagerAdapter;
import com.skycaster.wuhanmappingapp.base.BaseApplication;

/**
 * Created by 廖华凯 on 2017/8/4.
 */

public class MapTypeToggle extends FrameLayout {
    private ViewPager mViewPager;
    private TextView tvType;
    private MapTypeTogglePagerAdapter mAdapter;
    private MapTypeChangeListener mMapTypeChangeListener;



    public MapTypeToggle(@NonNull Context context) {
        this(context,null);
    }

    public MapTypeToggle(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MapTypeToggle(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FrameLayout view= (FrameLayout) View.inflate(context, R.layout.widget_map_type_toggle,null);
        mViewPager=view.findViewById(R.id.map_type_toggle_view_pager);
        tvType=view.findViewById(R.id.map_type_toggle_tv_description);
        addView(view);
//        view.requestDisallowInterceptTouchEvent(true);
//        mViewPager.requestDisallowInterceptTouchEvent(true);

        initViewPager();
    }

    private void initViewPager() {
        mAdapter=new MapTypeTogglePagerAdapter(mViewPager);
        mViewPager.setAdapter(mAdapter);
        int mapType = BaseApplication.getSharedPreferences().getInt(StaticData.MAP_TYPE, 0);
        switch (mapType){
            case 0:
                toggleMapType(MapType.MAP_TYPE_VECTOR);
                break;
            case 1:
                toggleMapType(MapType.MAP_TYPE_SATELLITE);
                break;
        }
        tvType.setText(mapType%2==0?"矢量地图":"卫星地图");

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvType.setText(position%2==0?"矢量地图":"卫星地图");
                if(mMapTypeChangeListener!=null){
                    mMapTypeChangeListener.onMapTypeChange(position%2==0?MapType.MAP_TYPE_VECTOR:MapType.MAP_TYPE_SATELLITE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void toggleMapType(MapType mapType){
        switch (mapType){
            case MAP_TYPE_SATELLITE:
                mViewPager.setCurrentItem(1);
                break;
            case MAP_TYPE_VECTOR:
                mViewPager.setCurrentItem(0);
                break;
        }
    }

    public void setMapTypeChangeListener(MapTypeChangeListener listener){
        mMapTypeChangeListener=listener;
    }

    public interface MapTypeChangeListener{
        void onMapTypeChange(MapType mapType);
    }

    public enum MapType{
        MAP_TYPE_VECTOR,MAP_TYPE_SATELLITE
    }
}
