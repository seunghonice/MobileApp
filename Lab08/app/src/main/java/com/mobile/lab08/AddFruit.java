package com.mobile.lab08;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by HongSeungho on 2017. 4. 27..
 */


/**
 *
 * custom widget을 만드려면
 * 생성자에 AttributeSet attrs 가 있어야 함.
 * 콜백 메서드 만드려면 아래와 같이 버튼 클릭 다 만들고 MainActivity에 인터페이스 만든거 갖다 붙여라.
 */
public class AddFruit extends LinearLayout implements View.OnClickListener {
    int imageNum = 0;
    AutoCompleteTextView edit;
    ImageView image;
    Button btn_add, btn_next;
    Context context;

    // xml에서도 사용하고 싶다면 이 형태의 생성자를 써야한다.
    public AddFruit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.fruit_add, this);
        edit = (AutoCompleteTextView) findViewById(R.id.f_name);
        image = (ImageView) findViewById(R.id.f_image1);
        btn_add = (Button) findViewById(R.id.f_b_add);
        btn_add.setOnClickListener(this);
        btn_next = (Button) findViewById(R.id.f_b_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add) {
            onAddListener.onAdd(edit.getText().toString(), Fruit.imageList[imageNum]);
        } else if (v == btn_next) {
            if (imageNum == Fruit.imageList.length-1) imageNum = -1;
            image.setImageResource(Fruit.imageList[++imageNum]);


        }
    }

    interface OnAddListener {
        void onAdd(String name, int imageNum);
    }
    // handler
    public OnAddListener onAddListener;

    public void setOnCustomAddListener(OnAddListener onaddListener) {
        this.onAddListener = onaddListener;
    }


}
