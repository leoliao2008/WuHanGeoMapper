package com.skycaster.wuhanmappingapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.base.BaseApplication;


/**
 * Created by 廖华凯 on 2017/8/4.
 */

public class MapTypeTogglePagerAdapter extends PagerAdapter {
    private ViewPager mViewPager;

    public MapTypeTogglePagerAdapter(ViewPager context) {
        mViewPager = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView imageView= (ImageView) View.inflate(mViewPager.getContext(), R.layout.item_map_type_toggle,null);
        if(position%2==0){
            imageView.setImageResource(R.drawable.ic_map_type_vector);
        }else {
            imageView.setImageResource(R.drawable.ic_map_type_satellite);
        }
        container.addView(imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                imageView.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mViewPager.getCurrentItem()==0){
                    mViewPager.setCurrentItem(1,true);
                    BaseApplication.getSharedPreferences().edit().putInt(StaticData.MAP_TYPE,1).apply();

                }else {
                    mViewPager.setCurrentItem(0,true);
                    BaseApplication.getSharedPreferences().edit().putInt(StaticData.MAP_TYPE,0).apply();
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
