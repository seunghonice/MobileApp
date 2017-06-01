package com.mobile.lab08;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class Fruit {
    String name;
    int imageNum;
    static final int[] imageList = {R.drawable.abocado,
            R.drawable.banana,
            R.drawable.cherry,
            R.drawable.cranberry,
            R.drawable.grape,
            R.drawable.kiwi,
            R.drawable.orange,
            R.drawable.watermelon};
    public Fruit(String name, int imageNum) {
        this.name = name;
        this.imageNum = imageNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageNum() {
        return imageNum;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }
}
