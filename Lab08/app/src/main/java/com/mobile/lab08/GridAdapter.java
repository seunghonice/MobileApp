package com.mobile.lab08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class GridAdapter extends BaseAdapter {
    ArrayList<Fruit> list;
    Context context;

    public GridAdapter(Context context, ArrayList<Fruit> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_grid_item, null);

        final TextView tv = (TextView) convertView.findViewById(R.id.text1);
        final ImageView iv = (ImageView) convertView.findViewById(R.id.image1);

        tv.setText(list.get(position).getName());
        iv.setImageResource(list.get(position).getImageNum());

//        if (convertView == null)
//            convertView = new GridItem(context);
//
//        ((GridItem) convertView).setData(list.get(position));
        return convertView;
    }

    public void addFruit(Fruit f) {
        list.add(f);
        notifyDataSetChanged();
    }
}
