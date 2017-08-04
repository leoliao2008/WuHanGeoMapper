package com.skycaster.wuhanmappingapp.bean;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class TabItem {
    private String title;
    private int imageSrc;

    public TabItem(String title, int imageSrc) {
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
