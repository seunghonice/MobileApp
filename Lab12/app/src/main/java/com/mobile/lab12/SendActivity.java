package com.mobile.lab12;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendActivity extends AppCompatActivity implements View.OnClickListener{
    SubThread subThread = new SubThread();
    EditText edit_name, edit_nameFromThread;
    Button btn_send;

    class SubThread extends Thread {
        SubHandler subHandler = new SubHandler();
        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }
    Handler mainHandler = new Handler() { // thread에서 보낸 메시지를 처리해야 하니까 handleMessage 구현
        @Override
        public void handleMessage(Message msg) {
            String name2 = (String) msg.obj;
            edit_nameFromThread.setText(name2);
        }
    };
    class SubHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String name = (String) msg.obj;
            name = "Hello " + name;

            Message msg2 = Message.obtain();
            msg2.obj = name;
            mainHandler.sendMessage(msg2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_send);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_nameFromThread = (EditText) findViewById(R.id.edit_nameFromThread);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        subThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); subThread.subHandler.getLooper().quit();
    }

    @Override
    public void onClick(View v) {
        String name = edit_name.getText().toString();
        Message msg = Message.obtain();
        msg.obj = name;
        subThread.subHandler.sendMessageDelayed(msg, 1000);
    }
}
