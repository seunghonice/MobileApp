package com.mobile.lab09_memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by HongSeungho on 2017. 5. 30..
 */

public class MemoAdapter extends BaseAdapter {
    ArrayList<Memo> list;
    Context context;

    public MemoAdapter(ArrayList<Memo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Memo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_memo_item, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.item_memo_name);
        name.setText(list.get(position).getDate()+".memo");
        return null;
    }
}
