package com.mobile.lab11_thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_seconds;
    Button btn_reset;
    ImageView image;
    TextView text_count;

    int[] images = {R.drawable.chicken, R.drawable.hamburger, R.drawable.pasta, R.drawable.pizza};
    boolean stopped = true;

    int seconds = 0;
    boolean loop = false;
    MyTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();

    }

    private void init() {
        edit_seconds = (EditText) findViewById(R.id.edit_seconds);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(this);
        text_count = (TextView) findViewById(R.id.text_count);
    }

    private class MyTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text_count.setText("시작부터 0 초");
            image.setImageResource(images[0]);
        }


        @Override
        protected Void doInBackground(Integer... params) {
            int i = 1;
            int imageIndex = 0;
            while (loop) {
                try {
                    Thread.sleep(1000);

                    if (i % seconds == 0) imageIndex++;
                    if (imageIndex > images.length - 1) imageIndex = 0;
                    publishProgress(i, imageIndex);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            text_count.setText("시작부터 " + values[0] + " 초");
            Log.d("values[0] % seconds = ?", values[0] + " % " + seconds + " = " + values[0] % seconds);
            if (values[0] % seconds == 0) {
                image.setImageResource(images[values[1]]);
            }
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_reset) {
            stopped = true;
            image.setImageResource(R.drawable.play);
            text_count.setVisibility(View.GONE);
            task = null;
        }
        if (v.getId() == R.id.image) {
            if (stopped && task == null) { // 처음 시작할 때
                stopped = false;
                loop = true;
                Log.d("start", "go");
                Log.d("loop", "" + loop);
                text_count.setVisibility(View.VISIBLE);
                seconds = Integer.parseInt(edit_seconds.getText().toString());
                task = new MyTask();
                task.execute(0);
            } else if (!stopped) { // 중간에 멈출 때
                Log.d("cancel!", "cancel");
                task.cancel(false);
                loop = false;
            }
        }
    }
}
