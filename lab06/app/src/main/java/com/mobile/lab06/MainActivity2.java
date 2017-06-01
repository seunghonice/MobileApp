package com.mobile.lab06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    EditText edit_name, edit_tel, edit_menu1, edit_menu2, edit_menu3, edit_homepage;
    RadioGroup radio_category;

    Button btn_cancel, btn_add;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main2);

        init();
    }

    private void init() {
        edit_name = (EditText) findViewById(R.id.main2_edit_name);
        radio_category = (RadioGroup) findViewById(R.id.main2_radio);
        edit_tel = (EditText) findViewById(R.id.main2_edit_tel);
        edit_menu1 = (EditText) findViewById(R.id.main2_edit_menu1);
        edit_menu2 = (EditText) findViewById(R.id.main2_edit_menu2);
        edit_menu3 = (EditText) findViewById(R.id.main2_edit_menu3);
        edit_homepage = (EditText) findViewById(R.id.main2_edit_homepage);

        btn_cancel = (Button) findViewById(R.id.main2_btn_cancel);
        btn_cancel.setOnClickListener(this);
        btn_add = (Button) findViewById(R.id.main2_btn_add);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main2_btn_cancel:
                setResult(RESULT_CANCELED);
                break;
            case R.id.main2_btn_add:
                Intent intent = getIntent();
                intent.putExtra("name", edit_name.getText().toString());
                RadioButton checked = (RadioButton) findViewById(radio_category.getCheckedRadioButtonId());
                if (checked.getId() == R.id.main2_radio1) {
                    intent.putExtra("category", 1);
                } else if (checked.getId() == R.id.main2_radio2) {
                    intent.putExtra("category", 2);
                } else {
                    intent.putExtra("category", 3);
                }
                intent.putExtra("tel", edit_tel.getText().toString());
                intent.putExtra("menu", new String[]{edit_menu1.getText().toString(), edit_menu2.getText().toString(), edit_menu3.getText().toString()});
                intent.putExtra("homepage", edit_homepage.getText().toString());
                long now = System.currentTimeMillis();
                date = new Date(now);
                SimpleDateFormat currentDate = new SimpleDateFormat("yyyyMMdd");
                intent.putExtra("regiDate", currentDate.format(date));
                setResult(RESULT_OK, intent);
                break;

        }
        finish();
    }
}
