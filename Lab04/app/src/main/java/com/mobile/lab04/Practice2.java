package com.mobile.lab04;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Practice2 extends AppCompatActivity {
    TabHost tabHost;
    FrameLayout fl;
    Button calc;
    EditText cmBMI;
    EditText kgBMI;
    TextView resultBMI;

    EditText input;
    TextView result;
    Button toMeter;
    Button toPyeong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_practice2);

        fl = (FrameLayout) findViewById(android.R.id.tabcontent);
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("A").setIndicator("BMI 계산기").setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                fl.setBackgroundResource(R.color.lightYel);
                calc = (Button) findViewById(R.id.calcBMI);
                cmBMI = (EditText) findViewById(R.id.cmBMI);
                kgBMI = (EditText) findViewById(R.id.kgBMI);
                resultBMI = (TextView) findViewById(R.id.resultBMI);
                calc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cmBMI.getText().equals("") || kgBMI.getText().equals("")) {
                            Toast.makeText(getApplicationContext(), "put cm and kg", Toast.LENGTH_SHORT).show();
                        } else {
                            double result = Integer.parseInt(kgBMI.getText().toString()) / Math.pow(2, Integer.parseInt(cmBMI.getText().toString()));
                            resultBMI.setText("");
                        }
                    }
                });
                return View.inflate(Practice2.this, R.layout.tab1, null);
            }
        }));
        tabHost.addTab(tabHost.newTabSpec("B").setIndicator("면적 계산기").setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                fl.setBackgroundResource(R.color.sky);
                input = (EditText) findViewById(R.id.input);
                result = (TextView) findViewById(R.id.result);
                toMeter = (Button) findViewById(R.id.toMeter);
                toPyeong = (Button) findViewById(R.id.toPyeong);
                toMeter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (input.getText().equals(""))
                            Toast.makeText(getApplicationContext(), "put pyeong or meter", Toast.LENGTH_SHORT).show();
                        else {
                            double resultValue = Double.parseDouble(input.getText().toString()) * 3.305785;
                            result.setText(input + "평은 " + resultValue + " 제곱미터 입니다.");
                        }
                    }
                });
                toPyeong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (input.getText().equals(""))
                            Toast.makeText(getApplicationContext(), "put pyeong or meter", Toast.LENGTH_SHORT).show();
                        else {
                            double resultValue = Double.parseDouble(input.getText().toString()) / 0.3025;
                            result.setText(input + "제곱미터는 " + resultValue + " 평 입니다.");
                        }
                    }
                });
                return View.inflate(Practice2.this, R.layout.tab2, null);
            }
        }));
    }
}
