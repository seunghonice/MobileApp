package com.mobile.lab11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class MyPainter extends View {
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;

    public MyPainter(Context context) {
        super(context);
        this.paint = new Paint();
        paint.setStrokeWidth(5);
        this.paint.setColor(Color.BLACK);
    }

    public MyPainter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        paint.setStrokeWidth(5);
        this.paint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap); // canvas에 쓰는게 bitmap에 그려진다.
        canvas.drawColor(Color.YELLOW); // canvas default 색상
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 20, 20, paint);
        bitmap.recycle();
    }

    private void drawStamp() {
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(img, 10, 10, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null)
            canvas.drawBitmap(bitmap, 0, 0, null);
    }

    int oldx = -1, oldy = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldx = x;
            oldy = y;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (oldx != -1) {
                canvas.drawLine(oldx, oldy, x, y, paint);
                oldx = x;
                oldy = y;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (oldx != -1) {
                canvas.drawLine(oldx, oldy, x, y, paint);
            }
            oldx = -1;
            oldy = -1;
        } else if (event.getX() >= 10 && event.getX() <= 30 && event.getY() >= 10 && event.getY() <= 30) {
            clear();
        }
        invalidate();
        return true;
    }

    public void clear() {
        bitmap.eraseColor(Color.WHITE);
        invalidate();
    }
}
