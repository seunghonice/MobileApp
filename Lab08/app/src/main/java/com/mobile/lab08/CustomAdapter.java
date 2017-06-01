package com.mobile.lab08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> list;
    public CustomAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, null);
        }
            final TextView tv = (TextView) convertView.findViewById(R.id.textView);
            final CheckBox c1 = (CheckBox) convertView.findViewById(R.id.checkBox);
            c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String msg = "선택되었습니다.";
                    if (!isChecked) msg = "선택되지 않았습니다.";
                    tv.setText(msg);
                }
            });
        return convertView;
    }
}
