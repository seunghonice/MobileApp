package com.mobile.lab10_graphics;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox check_stamp;
    Button btn_erase, btn_open, btn_save, btn_rotate, btn_move, btn_scale, btn_skew;
    MyCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
         canvas = (MyCanvas) findViewById(R.id.canvas);
        init();

    }

    private void init() {
        check_stamp = (CheckBox) findViewById(R.id.check_stamp);
        check_stamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    canvas.setStamp(true);
                } else {
                    canvas.setStamp(false);
                }
            }
        });
        btn_erase = (Button) findViewById(R.id.btn_erase);
        btn_erase.setOnClickListener(this);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(this);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_rotate.setOnClickListener(this);
        btn_move = (Button) findViewById(R.id.btn_move);
        btn_move.setOnClickListener(this);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_scale.setOnClickListener(this);
        btn_skew = (Button) findViewById(R.id.btn_skew);
        btn_skew.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_bluring:
                if (item.isChecked()) {
                    canvas.bluring();
                } else {
                    canvas.unBluring();
                }
                break;
            case R.id.menu_coloring:
                if (item.isChecked()) {
                    canvas.coloring();
                } else {
                    canvas.unColoring();
                }
                break;
            case R.id.menu_pen_width_big:
                if (item.isChecked()) {
                    canvas.paint.setStrokeWidth(5);
                } else {
                    canvas.paint.setStrokeWidth(1);
                }
                break;
            case R.id.menu_pen_color_red:
                canvas.paint.setColor(Color.RED);
                break;
            case R.id.menu_pen_color_blue:
                canvas.paint.setColor(Color.BLUE);
                break;
            case R.id.menu_pen_color_black:
                canvas.paint.setColor(Color.BLACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.canvas:

                break;
            case R.id.btn_erase:
                break;
            case R.id.btn_open:
                break;
            case R.id.btn_save:
                if (canvas.save(getExternalPath()+"draw/paint.png"))
                    Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_rotate:
                break;
            case R.id.btn_move:
                break;
            case R.id.btn_scale:
                break;
            case R.id.btn_skew:
                break;
        }
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
}
