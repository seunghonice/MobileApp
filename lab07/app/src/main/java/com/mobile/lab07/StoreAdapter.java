package com.mobile.lab07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class StoreAdapter extends BaseAdapter {
    ArrayList<Store> stores;
    private Context context;
    private boolean isChecked = false;
    private ViewGroup parent;

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
        this.parent = parent;
        ImageView image = (ImageView) convertView.findViewById(R.id.item_image);
        TextView name = (TextView) convertView.findViewById(R.id.item_name);
        TextView tel = (TextView) convertView.findViewById(R.id.item_tel);

        image.setImageResource(Store.CATEGORY[stores.get(position).getCategoryNum()]);
        name.setText(stores.get(position).getName());
        tel.setText(stores.get(position).getTel());

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.item_checkbox);
        if (isChecked) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setChecked(false);
            checkBox.setVisibility(View.INVISIBLE);
        }

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

    Comparator<Store> sortByName = new Comparator<Store>() {
        @Override
        public int compare(Store o1, Store o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };
    public void sortByName() {
        Collections.sort(stores, sortByName);
        notifyDataSetChanged();
    }

    Comparator<Store> sortByCategory = new Comparator<Store>() {
        @Override
        public int compare(Store o1, Store o2) {
            return o1.getCategoryNum() - o2.getCategoryNum();
        }
    };
    public void sortByCategory() {
        Collections.sort(stores, sortByCategory);
        notifyDataSetChanged();
    }




    public void toggleCheckBox() {
        isChecked = isChecked? false : true;
        notifyDataSetChanged();
    }

    public void removeSelectedItems() {
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (((CheckBox)parent.getChildAt(i).findViewById(R.id.item_checkbox)).isChecked())
                removeItem(i);
        }
        toggleCheckBox();
    }
}
