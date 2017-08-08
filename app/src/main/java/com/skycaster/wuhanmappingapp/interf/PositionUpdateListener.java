package com.skycaster.wuhanmappingapp.interf;

import com.skycaster.inertial_navi_lib.GPGGABean;
import com.tianditu.android.maps.MapView;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public interface PositionUpdateListener {
    void onPositionUpdate(GPGGABean bean, MapView mapView);
}
