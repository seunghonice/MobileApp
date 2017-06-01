package com.mobile.lab07;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    TextView text_name, text_menu1, text_menu2, text_menu3, text_tel, text_homepage, text_regiDate;
    ImageView image, image_tel, image_homepage;
    Button btn_back;

    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main3);

        init();
        store = (Store) getIntent().getSerializableExtra("store");
        setInfo();
    }

    private void init() {
        text_name = (TextView) findViewById(R.id.main3_text_name);
        image = (ImageView) findViewById(R.id.main3_image);
        text_menu1 = (TextView) findViewById(R.id.main3_text_menu1);
        text_menu2 = (TextView) findViewById(R.id.main3_text_menu2);
        text_menu3 = (TextView) findViewById(R.id.main3_text_menu3);
        text_tel = (TextView) findViewById(R.id.main3_text_tel);
        text_homepage = (TextView) findViewById(R.id.main3_text_homepage);
        image_tel = (ImageView) findViewById(R.id.main3_image_tel);
        image_tel.setOnClickListener(this);
        image_homepage = (ImageView) findViewById(R.id.main3_image_homepage);
        image_homepage.setOnClickListener(this);
        text_regiDate = (TextView) findViewById(R.id.main3_text_regidate);
        btn_back = (Button) findViewById(R.id.main3_btn_back);
        btn_back.setOnClickListener(this);
    }

    private void setInfo() {
        text_name.setText(store.getName());
        image.setImageResource(Store.CATEGORY[store.getCategoryNum()]);
        text_menu1.setText(store.getMenu()[0]);
        text_menu2.setText(store.getMenu()[1]);
        text_menu3.setText(store.getMenu()[2]);
        text_tel.setText(store.getTel());
        text_homepage.setText(store.getHomepage());
        text_regiDate.setText(store.getRegiDate());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main3_image_tel:
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:/" + store.getTel())));
                break;
            case R.id.main3_image_homepage:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + store.getHomepage())));
                break;
            case R.id.main3_btn_back:
                finish();
                break;
        }
    }
}
