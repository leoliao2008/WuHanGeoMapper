package com.skycaster.wuhanmappingapp.customized;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.skycaster.inertial_navi_lib.GPGGABean;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseApplication;
import com.skycaster.wuhanmappingapp.interf.PositionUpdateListener;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.OverlayItem;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SkyCasterPositioningOverlay extends Overlay implements PositionUpdateListener {

    private OverlayItem mItem;
    private Context mContext;

    public SkyCasterPositioningOverlay(Context context) {
        mContext = context;
    }

    @Override
    public void onPositionUpdate(GPGGABean bean, final MapView mapView) {
        Location location = bean.getLocation();
        final GeoPoint geoPoint=new GeoPoint((int)(location.getLatitude()*1E6),(int)(location.getLongitude()*1E6));
        mItem = new OverlayItem(geoPoint,"当前位置",geoPoint.toString());
        mapView.postInvalidate();
        BaseApplication.post(new Runnable() {
            @Override
            public void run() {
                mapView.getController().animateTo(geoPoint);
            }
        });
    }


    @Override
    public boolean onLongPress(GeoPoint p, MapView mapView) {
        return super.onLongPress(p, mapView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event, MapView mapView) {
        return super.onKeyDown(keyCode, event, mapView);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event, MapView mapView) {
        return super.onKeyUp(keyCode, event, mapView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) {
        return super.onTouchEvent(event, mapView);
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);
        if(mItem!=null){
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_location_blue);
            Point point = mapView.getProjection().toPixels(mItem.getPoint(), null);
            int left=point.x-drawable.getIntrinsicWidth()/2;
            int top=point.y-drawable.getIntrinsicHeight()/2;
            int right=point.x+drawable.getIntrinsicWidth()/2;
            int bottom=point.y+drawable.getIntrinsicHeight()/2;
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(canvas);
        }
    }

}
