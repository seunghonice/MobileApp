package com.mobile.lab03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;

public class Practice02 extends AppCompatActivity {
    Switch start;
    LinearLayout info, contents;
    Chronometer cm;
    DatePicker datePicker;
    TimePicker timePicker;
    Button prev, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout02);
        init();
        start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    info.setVisibility(View.VISIBLE);
                    contents.setVisibility(View.VISIBLE);
                    chronometer.setBase(0);
                    chronometer.setFormat();
                    chronometer.setCountDown(false);
                    chronometer.start();
                }
            }
        });

    }

    private void init() {
        start = (Switch) findViewById(R.id.switch_start);
        info = (LinearLayout) findViewById(R.id.layout_info);
        contents = (LinearLayout) findViewById(R.id.layout_contents);
        cm = (Chronometer) findViewById(R.id.chronometer);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        prev = (Button) findViewById(R.id.btn_prev);
        next = (Button) findViewById(R.id.btn_next);
    }
}
