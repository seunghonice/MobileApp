package com.mobile.lab03;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Practice01 extends AppCompatActivity implements View.OnClickListener{
    EditText grade1;
    EditText grade2;
    EditText grade3;
    Button btn1;
    Button btn2;
    TextView total;
    TextView average;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout01);
        init();


    }

    private void init() {
        grade1 = (EditText) findViewById(R.id.edit_grade1);
        grade2 = (EditText) findViewById(R.id.edit_grade2);
        grade3 = (EditText) findViewById(R.id.edit_grade3);
        btn1 = (Button) findViewById(R.id.btn_calcGrades);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn_init);
        btn2.setOnClickListener(this);
        total = (TextView) findViewById(R.id.text_total);
        average = (TextView) findViewById(R.id.text_average);
        img = (ImageView) findViewById(R.id.img_grade);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_calcGrades:
                if (grade1.getText().toString().equals("") ||
                    grade2.getText().toString().equals("") ||
                    grade3.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Put grades", Toast.LENGTH_SHORT).show();
                    break;
                }
                String g1 = grade1.getText().toString();
                String g2 = grade2.getText().toString();
                String g3 = grade3.getText().toString();
                int totalScore = calcScore(g1, g2, g3);
                int averageScore = totalScore/3;
                total.setText(totalScore + "점");
                average.setText(averageScore + "점");
                img.setVisibility(View.VISIBLE);
                if (averageScore >= 90) {
                    img.setImageResource(R.drawable.aa);
                } else if (averageScore >= 80) {
                    img.setImageResource(R.drawable.bb);
                } else if (averageScore >= 70) {
                    img.setImageResource(R.drawable.cc);
                } else if (averageScore >= 60) {
                    img.setImageResource(R.drawable.dd);
                } else {
                    img.setImageResource(R.drawable.f);
                }
                break;
            case R.id.btn_init:
                grade1.setText("");
                grade2.setText("");
                grade3.setText("");
                total.setText("0점");
                average.setText("0점");
                img.setImageResource(0);
                img.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "초기화 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private int calcScore(String grade1, String grade2, String grade3) {
        return Integer.parseInt(grade1) + Integer.parseInt(grade2) + Integer.parseInt(grade3);
    }
}
