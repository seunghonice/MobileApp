package com.mobile.lab08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.id.list;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class GridItem extends LinearLayout {
    TextView tv;
    ImageView iv;

    public GridItem(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_grid_item, null);

        tv = (TextView) view.findViewById(R.id.text1);
        iv = (ImageView) view.findViewById(R.id.image1);
    }

    public void setData(Fruit fruit) {
        tv.setText(fruit.getName());
        iv.setImageResource(fruit.getImageNum());
    }
}
