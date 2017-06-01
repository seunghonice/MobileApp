package com.mobile.lab10_graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by HongSeungho on 2017. 5. 30..
 */

public class MyCanvas extends View {
    Paint paint;
    Bitmap bitMap, stamp;
    Canvas canvas;
    boolean isCheckedStamp = false;
    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitMap != null)
            canvas.drawBitmap(bitMap, 0, 0, paint);
    }

    public void setStamp(boolean check) {
        isCheckedStamp = check;
    }

    private void drawStamp(int x, int y) {
        canvas.drawBitmap(stamp, x - stamp.getWidth() / 2, y - stamp.getHeight() / 2, null);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitMap);
        canvas.drawColor(Color.YELLOW);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);


        stamp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
    }

    float startX = -1, startY = -1, endX = -1, endY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isCheckedStamp) {
                drawStamp(x, y);
//                invalidate();
            } else {
                startX = event.getX();
                startY = event.getY();
//                canvas.drawRect(x, y, x + 1, y + 1, paint);
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (startX != -1 && !isCheckedStamp) {
                canvas.drawLine(startX, startY, x, y, paint);
                invalidate();
                startX = x;
                startY = y;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (!isCheckedStamp) {
                endX = event.getX();
                endY = event.getY();
                invalidate();
            }
        }

        return true;
    }

    public void rotate() {
    }

    public void bluring() {
        BlurMaskFilter blur = new BlurMaskFilter(15, BlurMaskFilter.Blur.INNER);
        paint.setMaskFilter(blur);
        invalidate();
    }

    public void unBluring() {
        paint.setMaskFilter(null);
        invalidate();
    }

    public void coloring() {
        float[] matrixarray = {
                2f, 0f, 0f, 0f, -25f,
                0f, 2f, 0f, 0f, -25f,
                0f, 0f, 2f, 0f, -25f,
                0f, 0f, 0f, 1f, 0f
        };
        ColorMatrix matrix = new ColorMatrix(matrixarray);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    public void unColoring() {
        paint.setColorFilter(null);
    }

    public void penWithBig() {

    }

    public void penColorRed() {

    }

    public void penColorBlue() {

    }

    public boolean save(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            bitMap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
        }
        return false;
    }

    public void clear() {
        bitMap.eraseColor(Color.YELLOW);
        invalidate();
    }
}
