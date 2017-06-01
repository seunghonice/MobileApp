package com.mobile.lab07;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_add, btn_name, btn_category, btn_select, btn_delete;
    ListView listView;
    StoreAdapter adapter;
    EditText edit_search;

    private static final int REQUESTCODE_ACTIVITY2 = 0x00000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
    }

    private void init() {
        btn_add = (Button) findViewById(R.id.main1_btn_add);
        btn_add.setOnClickListener(this);
        btn_name = (Button) findViewById(R.id.main1_btn_name);
        btn_name.setOnClickListener(this);
        btn_category = (Button) findViewById(R.id.main1_btn_category);
        btn_category.setOnClickListener(this);
        btn_select = (Button) findViewById(R.id.main1_btn_select);
        btn_select.setOnClickListener(this);
        btn_delete = (Button) findViewById(R.id.main1_btn_delete);
        btn_delete.setOnClickListener(this);
        edit_search = (EditText) findViewById(R.id.main1_edit_search);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                if (search.length() > 0)
                    listView.setFilterText(search);
                else
                    listView.clearTextFilter();
            }
        });
        listView = (ListView) findViewById(R.id.main1_listview);
        adapter = new StoreAdapter(new ArrayList<Store>(), this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                intent.putExtra("store", adapter.getItem(position));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("삭제하시겠습니까?");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
                return true;
            }
        });

        adapter.addItem(new Store("가게1", 2, "01099566752", new String[]{"맛있는거1", "맛있는거2", "맛있는거3"}, "naver.com", "20170427"));
        adapter.addItem(new Store("함에4", 0, "01099566752", new String[]{"재밌는거1", "재밌는거2", "재밌는거3"}, "naver.com", "20170427"));
        adapter.addItem(new Store("라게1", 1, "01099566752", new String[]{"맛없는거1", "맛없는거2", "맛없는거3"}, "naver.com", "20170427"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE_ACTIVITY2) {
            if (resultCode == RESULT_OK) {
                Store store = new Store(
                        data.getStringExtra("name"),
                        data.getIntExtra("category", 1),
                        data.getStringExtra("tel"),
                        data.getStringArrayExtra("menu"),
                        data.getStringExtra("homepage"),
                        data.getStringExtra("regidate"));
                adapter.addItem(store);
            }

        }
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivityForResult(intent, REQUESTCODE_ACTIVITY2);
        } else if (v == btn_name) {
            adapter.sortByName();
        } else if (v == btn_category) {
            adapter.sortByCategory();
        } else if (v == btn_select) {
            adapter.toggleCheckBox();
            btn_select.setVisibility(View.GONE);
            btn_delete.setVisibility(View.VISIBLE);
        } else if (v == btn_delete) {
            adapter.removeSelectedItems();
            btn_delete.setVisibility(View.GONE);
            btn_select.setVisibility(View.VISIBLE);
        }
    }
}
