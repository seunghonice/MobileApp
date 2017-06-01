package com.mobile.lab11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HongSeungho on 2017. 5. 18..
 */

public class MyCanvas2 extends View {
    public MyCanvas2(Context context) {
        super(context);
    }

    public MyCanvas2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(startX, startY, stopX, stopY, paint);
    }

    private float startX, startY, stopX, stopY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = event.getX(); startY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            stopX = event.getX(); stopY = event.getY();
            invalidate();
        }
        return true;
    }
}
