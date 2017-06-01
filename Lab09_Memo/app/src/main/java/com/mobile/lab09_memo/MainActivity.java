package com.mobile.lab09_memo;

import android.icu.text.LocaleDisplayNames;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linear_list;
    TextView text_count;
    Button btn_write;
    ListView listView;

    ArrayAdapter adapter;
    LinearLayout linear_memo;
    Button btn_save, btn_cancel;
    DatePicker datePicker;
    EditText edit_contents;

    ArrayList<String> memoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
    }

    private void init() {
        linear_list = (LinearLayout) findViewById(R.id.linear_list);
        text_count = (TextView) findViewById(R.id.text_count);
        btn_write = (Button) findViewById(R.id.btn_write);
        btn_write.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);

        linear_memo = (LinearLayout) findViewById(R.id.linear_memo);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        edit_contents = (EditText) findViewById(R.id.edit_contents);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, memoList);
//        File[] memoFileList = new File(mkExternalDirectory()).listFiles();
//        for (File memoFile: memoFileList) {
//            memoList.add(memoFile.getName());
//            adapter.notifyDataSetChanged();
//        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        text_count.setText("등록된 메모 개수: " + adapter.getCount());
    }

    private String mkExternalDirectory() {
        String path = getExternalPath() + "diary";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            String msg = file.getAbsolutePath() + " 생성";
            if (!file.isDirectory())
                msg = "생성 오류";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Log.d("path: ", "exist");
            Toast.makeText(this, file.getAbsolutePath() + "가 이미 존재함", Toast.LENGTH_SHORT).show();
        }
        return path;
    }

    private String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
            //sdPath = "/mnt/sdcard/";
        } else
            sdPath = getFilesDir() + "";
        Toast.makeText(getApplicationContext(), sdPath, Toast.LENGTH_SHORT).show();
        return sdPath;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_write) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String date = df.format(new Date());
            datePicker.updateDate(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
            edit_contents.setText("");
            linear_list.setVisibility(View.GONE);
            linear_memo.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.btn_save) {
            try {
                String memoName = String.valueOf(datePicker.getYear()).substring(2, 4) + "-" + String.format("%02d", datePicker.getMonth()) + "-" + String.format("%02d", datePicker.getDayOfMonth()) + ".memo";
                String path = getExternalPath() + "diary/";
                File file = new File(path + memoName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(edit_contents.getText().toString().getBytes());
                fos.close();
                Toast.makeText(this, memoName, Toast.LENGTH_SHORT).show();
                Log.d("memoName", memoName);
                memoList.add(memoName);
                adapter.notifyDataSetChanged();
                linear_memo.setVisibility(View.GONE);
                linear_list.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage() + ":오류데스" + getFilesDir(),
                        Toast.LENGTH_SHORT).show();
            }
            linear_memo.setVisibility(View.GONE);
            linear_list.setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.btn_cancel) {
            linear_memo.setVisibility(View.GONE);
            linear_list.setVisibility(View.VISIBLE);
        }
    }
}
