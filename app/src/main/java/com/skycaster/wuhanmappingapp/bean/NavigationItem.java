package com.skycaster.wuhanmappingapp.bean;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class NavigationItem {
    private String title;
    private int imageSrc;

    public NavigationItem(String title, int imageSrc) {
        this.title = title;
        this.imageSrc = imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public int getImageSrc() {
        return imageSrc;
    }
}
