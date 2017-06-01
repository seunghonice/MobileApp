package com.mobile.lab04;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends ActivityGroup {
    TextView hello;
    TabHost tabhost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        hello = (TextView) findViewById(R.id.helloworld);
        registerForContextMenu(hello);
        setTitle("TabHost");


//        tabhost = (TabHost) findViewById(R.id.tabhost);
//        tabhost.setup(getLocalActivityManager());
//
//        tabhost.addTab(tabhost.newTabSpec("A").setContent(R.id.tab1).setIndicator("날짜선택"));
//        tabhost.addTab(tabhost.newTabSpec("B").setIndicator("시간선택").setContent(new TabHost.TabContentFactory() {
//            @Override
//            public View createTabContent(String tag) {
//                View view = View.inflate(MainActivity.this, R.layout.tab1, null);
////                TextView text = (TextView) view.findViewById(R.id.textTab2);
////                text.append("!!");
//                return view;
//            }
//        }));
//        tabhost.addTab(tabhost.newTabSpec("C").setContent(new Intent(this, TabActivity3.class)).setIndicator("확인"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.item1) {
//            hello.append("!!");
//            item.setChecked(true);
//        }
//        if (item.getItemId() == R.id.item2) {
//            hello.append("--");
//            item.setChecked(true);
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        if (v.getId() == R.id.helloworld) {
//            menu.setHeaderTitle("메뉴");
//            getMenuInflater().inflate(R.menu.contextmenu, menu);

//        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.helloworld) {
//            hello.setBackgroundColor(Color.BLUE);
//        }
        return super.onContextItemSelected(item);
    }
}
