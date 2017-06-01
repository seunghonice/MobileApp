package com.mobile.lab12;

/**
 * Runnable 객체를 생성하여 handler에 보내는 방법
 *  - Thread 클래스를 상속받은 클래스를 하나 만들어 run() 을 override 하는데
 *    그 안에 Main Thread에서 만든 handler.post(new Runnable() {
 *        @override public void run() { 보낼 내용 }
 *    }
 * Message 객체를 만들어 handler에 보내는 방법
 *  - 
 */

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    class MyThread extends Thread {
        @Override
        public void run() {
            for (count = 1; count <= 10; count++) {
                try {
                    sleep(1000);
                    // Message 객체를 보내는 방법, Runnable객체를 보내는 방법* 사용
                    // Main Thread에서 만든 Handler 를 이용해서 RUnnable 객체를 보내 메시지 큐에 작업을 올림.
                    Message msg = handler.obtainMessage(0);
                    handler.sendEmptyMessage(0);
                    handler.sendMessage(msg);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            text_count.setText("Hello World! " + count);
//                        }
//                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    TextView text_count;
    Button btn_start;
    MyThread th = new MyThread();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            text_count.setText("Hello World! " + count);
        }
    };

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        text_count = (TextView) findViewById(R.id.text_count);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_start) {
            th.setDaemon(true);
            th.start();
        }
    }
}
