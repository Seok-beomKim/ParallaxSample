package com.hyosung.parallaxsample;

import android.graphics.drawable.Drawable;

/**
 * Created by SBKim on 2016-06-13.
 */
public class ListViewItem {
    private Drawable image ;
    private String titleStr ;

    public void setImage(Drawable image) {
        this.image = image ;
    }

    public void setTitle(String title) {
        this.titleStr = title ;
    }

    public Drawable getImage() {
        return this.image ;
    }

    public String getTitle() {
        return this.titleStr ;
    }

}