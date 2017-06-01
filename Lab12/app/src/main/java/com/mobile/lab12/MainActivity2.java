package com.mobile.lab12;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button btn_start, btn_stop;
    TextView text_progress;
    ProgressBar bar_progress;
    MyTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_task);
        btn_start = (Button) findViewById(R.id.btn_processStart);
        btn_start.setOnClickListener(this);
        btn_stop = (Button) findViewById(R.id.btn_processStop);
        btn_stop.setOnClickListener(this);
        text_progress = (TextView) findViewById(R.id.text_progress);
        bar_progress = (ProgressBar) findViewById(R.id.bar_progress);
    }
    class MyTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() { // UI 를 직접 건드릴 수 있는 영역
            super.onPreExecute();
            bar_progress.setProgress(0);
            text_progress.setTextColor(Color.RED);
            text_progress.setText("진행률 : 0%");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 1; i < 100; i ++) {
                try {
                    Log.d("i", ""+i);
                    Thread.sleep(200);
                    publishProgress(i,2,3,4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar_progress.setProgress(values[0]);
            text_progress.setText("진행률 : " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            bar_progress.setProgress(100);
            text_progress.setText("완료되었습니다.");
            text_progress.setBackgroundColor(Color.BLUE);
        }

        @Override
        protected void onCancelled() {
            text_progress.setText("취소되었습니다.");
            super.onCancelled();
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_processStart) {
            task = new MyTask();
            task.execute(0);
        } else {
            task.cancel(true);
            task = null;
        }
    }
}
