package com.mobile.lab09;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    LinearLayout layout_top;
    ProgressDialog dialog;
    Handler handler = new Handler();
    EditText edit_url;
    Button btn_go;
    ListView listview;
    Animation animation;
    ListAdapter adapter;
    ArrayList<Site> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
    }


    private void init() {
        webView = (WebView) findViewById(R.id.webview);
        dialog = new ProgressDialog(this);
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        layout_top = (LinearLayout) findViewById(R.id.layout_top);
        edit_url = (EditText) findViewById(R.id.edit_url);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(edit_url.getText().toString());

            }
        });
        listview = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new ListAdapter(this, list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layout_top.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);
                webView.loadUrl("http://" + adapter.getItem(position).getUrl());
                webView.setVisibility(View.VISIBLE);
            }
        });
        listview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("삭제하시겠습니까?");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        adapter.removeItem(which);
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.setMessage("Loading...입니당");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 100) dialog.dismiss();
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.loadUrl("http://www.google.com/");

        webView.addJavascriptInterface(new JavaScriptMethods(), "myApp");
        layout_top.setFocusable(true);
        layout_top.setFocusableInTouchMode(true);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addurl:
                if (webView.getVisibility() == View.GONE) webView.setVisibility(View.VISIBLE);
                if (listview.getVisibility() == View.VISIBLE) listview.setVisibility(View.GONE);
                webView.loadUrl("file:///android_asset/www/urladd.html");

                layout_top.setAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        layout_top.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animation.start();
                break;
            case R.id.listurl:
                webView.setVisibility(View.GONE);
                layout_top.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }


    class JavaScriptMethods {
        JavaScriptMethods() {
        }

        @JavascriptInterface
        public void displayToast() {
            Toast.makeText(getApplicationContext(), "Image Clicked", Toast.LENGTH_SHORT).show();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:changeImage()");
                }
            });
        }

        @JavascriptInterface
        public void addUrl(final String siteName, final String siteUrl) {
            if (adapter.containsUrl(siteUrl)) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:displayMsg()");
                    }
                });
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addItem(new Site(siteName, siteUrl));
                    }
                });
            }
        }
    }
}

