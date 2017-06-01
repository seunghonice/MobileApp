package com.mobile.lab09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HongSeungho on 2017. 5. 11..
 */

public class ListAdapter extends BaseAdapter {
    ArrayList<Site> list;
    Context context;

    public ListAdapter(Context context, ArrayList<Site> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Site getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_item, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.item_name);
        TextView url = (TextView) convertView.findViewById(R.id.item_url);
        name.setText(list.get(position).getName());
        url.setText(list.get(position).getUrl());
        return convertView;
    }

    public void addItem(Site item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public boolean containsName(String name) {
        for (Site site: list) {
            if (site.getName().equals(name)) return true;
        }
        return false;
    }
    public boolean containsUrl(String url) {
        for (Site site: list) {
            if (site.getUrl().equals(url)) return true;
        }
        return false;
    }

}
