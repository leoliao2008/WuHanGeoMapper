package com.skycaster.wuhanmappingapp.customized;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by 廖华凯 on 2017/8/4.
 */

public class FlipTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if(position<=1&&position>0){
            page.setPivotX(page.getWidth()/2);
            page.setRotationY(180*position);
            int with=page.getWidth();
            page.setTranslationX(-with+with*(1-position));
        } else if(position==0){
            page.setTranslationX(0);
        } else if(position <0&&position>=-1) {
            page.setPivotX(page.getWidth()/2);
            page.setRotationY(180*position);
            page.setTranslationX(Math.abs(page.getWidth()*position));
        }
    }
}
