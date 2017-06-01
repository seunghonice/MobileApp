package com.mobile.lab04;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HongSeungho on 2017. 3. 29..
 */

public class Practice1 extends AppCompatActivity{
    RelativeLayout layout;
    ImageView image;
    TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_practice1);
        setTitle("메뉴를 눌러보세요");

        layout = (RelativeLayout) findViewById(R.id.practice1_layout);
        image = (ImageView) findViewById(R.id.practice1_image);
        text = (TextView) findViewById(R.id.practice1_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.toRed:
                layout.setBackgroundResource(R.color.red);
                break;
            case R.id.toYellow:
                layout.setBackgroundResource(R.color.yellow);
                break;
            case R.id.toBlue:
                layout.setBackgroundResource(R.color.blue);
                break;
            case R.id.rotateImage:
                if (image.isShown()) {
                    image.setRotation(image.getRotation()+30);
                } else {
                    Toast.makeText(getApplicationContext(), "Show image, first", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.showTitle:
                if (item.isChecked()) { // 체크되어있으면
                    item.setChecked(false);
                    text.setVisibility(View.INVISIBLE);
                } else {
                    if (image.isShown()) {
                        item.setChecked(true);
                        text.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Show image, first", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.zoomImage:
                if (item.isChecked()) { // 체크되어있으면
                    image.setScaleX(1);
                    image.setScaleY(1);
                    item.setChecked(false);
                } else { // 체크 안되어있으면
                    if (image.isShown()) { // 이미지가 있으면
                        image.setScaleX((float) Math.sqrt(2.0));
                        image.setScaleY((float) Math.sqrt(2.0));
                        item.setChecked(true);
                    } else { // 이미지가 없으면
                        Toast.makeText(getApplicationContext(), "Show image, first", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.chickenImage:
                image.setImageResource(R.drawable.chicken);
                image.setVisibility(View.VISIBLE);
                item.setChecked(true);
                text.setText("겁나 맛있는 치킨");
                break;
            case R.id.spaghettiImage:
                image.setImageResource(R.drawable.spaghetti);
                image.setVisibility(View.VISIBLE);
                item.setChecked(true);
                text.setText("새콤한 스파게티");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

