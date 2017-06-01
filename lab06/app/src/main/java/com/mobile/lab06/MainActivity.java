package com.mobile.lab06;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView listNum;
    Button addStore;
    ListView listView;
    StoreAdapter<String> adapter;


    private static final int REQUESTCODE_ACTIVITY2 = 0x00000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
    }

    private void init() {
        listNum = (TextView) findViewById(R.id.main1_text_listnum);
        addStore = (Button) findViewById(R.id.main1_btn_add);
        addStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, REQUESTCODE_ACTIVITY2);
            }
        });
        listView = (ListView) findViewById(R.id.main1_listview);
        adapter = new StoreAdapter<>(new ArrayList<Store>(), this);
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
                        listNum.setText("맛집 리스트(" + adapter.getCount() + "개)");
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
                return true;
            }
        });
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
                listNum.setText("맛집 리스트(" + adapter.getCount() + "개)");
            }

        }
    }
}
