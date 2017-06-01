package com.mobile.lab07;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class Store implements Serializable {
    private String name;
    private String tel;
    private String[] menu;
    private String homepage;
    private String regiDate;
    private int categoryNum;
    public static final int[] CATEGORY= {R.drawable.chicken, R.drawable.pizza, R.drawable.hamburger};

    public Store(String name, int categoryNum, String tel, String[] menu, String homepage, String regiDate) {
        this.name = name;
        this.categoryNum = categoryNum;
        this.tel = tel;
        this.menu = menu;
        this.homepage = homepage;
        this.regiDate = regiDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(String regiDate) {
        this.regiDate = regiDate;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }
}
