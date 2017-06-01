package com.mobile.lab11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HongSeungho on 2017. 5. 18..
 */

public class MyCanvas extends View {

    Rect rect;

    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        rect = new Rect(10, 10, 100, 100);
        if (rect.contains(500, 500))
            canvas.drawRect(rect, paint);

        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(10, canvas.getHeight()/2, 100, 100, paint);

        paint.setTextSize(70);
        canvas.drawText("Click!", 300, 300, paint);

        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawCircle(300, 300, 50, paint);

        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(img, 300, 350, null);

        Bitmap smallImg = Bitmap.createScaledBitmap(img, img.getWidth()/2, img.getHeight()/2, false);
        canvas.drawBitmap(smallImg, 450, 350, null);
        Bitmap bigImg = Bitmap.createScaledBitmap(img, img.getWidth()*2, img.getHeight()*2, false);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        if (x >= 10 && x <= 100 && y >= 10 && y <= 100) {
//            Toast.makeText(getContext(), "Red Button", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), "Not Red Button", Toast.LENGTH_SHORT).show();
//        }

        return true; // true 로 해줘야 이벤트를 받아서 처리를 해주겠다. 라는 뜻
    }
}
