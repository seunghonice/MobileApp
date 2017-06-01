package com.mobile.lab11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HongSeungho on 2017. 5. 18..
 */

public class MyCanvas3 extends View {
    String operationType = "";

    public MyCanvas3(Context context) {
        super(context);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public MyCanvas3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Bitmap bigImg = Bitmap.createScaledBitmap(img, img.getWidth() * 2, img.getHeight() * 2, false);

        int cenX = (this.getWidth() - bigImg.getWidth())/2;
        int cenY = (this.getHeight() - bigImg.getHeight())/2;
        if (operationType.equals("rotate")) {
            canvas.rotate(45, this.getWidth() / 2, getWidth() / 2);
            canvas.drawBitmap(bigImg, cenX, cenY, paint);

        }

        float[] matrix = {
                2f,0,0,0,-25f,
                0,2f,0,0,-25f,
                0,0,2f,0,-25f,
                0,0,0,1f,0
        };
        ColorMatrix colorMatrix = new ColorMatrix(matrix);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bigImg, 10, 10, paint);
    }

    public void setOpertionType(String operationType) {
        this.operationType = operationType;
        invalidate();
    }
}
