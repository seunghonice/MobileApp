package com.mobile.lab06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class StoreAdapter<Stroe> extends BaseAdapter {
    ArrayList<Store> stores;
    private Context context;

    public StoreAdapter(ArrayList<Store> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public Store getItem(int position) {
        return stores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_listview_item, null);
        }
        TextView text = (TextView) convertView.findViewById(R.id.listview_item);
        text.setText(stores.get(position).getName());
        return convertView;
    }

    public void removeItem(int position) {
        stores.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(Store store) {
        stores.add(store);
        notifyDataSetChanged();
    }
}
