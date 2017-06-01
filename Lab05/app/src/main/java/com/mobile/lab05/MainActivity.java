package com.mobile.lab05;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editX;
    EditText editY;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        init();
    }

    public void init() {
        editX = (EditText) findViewById(R.id.editX);
        editY = (EditText) findViewById(R.id.editY);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMultiChoiceItems()
        dlg.setMultiChoiceItems()
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Toast.makeText(getApplicationContext(), "일반 메시지 창입니다", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast toast = Toast.makeText(getApplicationContext(), "위치지정 메시지 창입니다", Toast.LENGTH_SHORT);
                if (editX.getText().toString().equals("") || editY.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "X좌표 Y좌표 입력!", Toast.LENGTH_SHORT).show();
                else {
                    int x = Integer.parseInt(editX.getText().toString());
                    int y = Integer.parseInt(editY.getText().toString());
                    toast.setGravity(Gravity.LEFT | Gravity.TOP, x, y);
                    toast.show();
                }
                break;
            case R.id.btn3:
                View view = View.inflate(this, R.layout.layout_mytoast, null);
                TextView text3 = (TextView) view.findViewById(R.id.toastMsg);
                text3.setText("레이아웃으로 만든 Toast");
                Toast toast3 = new Toast(this);
                toast3.setDuration(Toast.LENGTH_SHORT);
                toast3.setView(view);
                toast3.show();
                break;
            case R.id.btn4:
                Snackbar.make(v, "Snackbar 데스네", 2000).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Hi", 1000).show();
                    }
                }).show();
                break;
        }
    }
}
