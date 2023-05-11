package com.example.androidtest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidtest.R;

public class TranslateView extends View {
    public TranslateView(Context context){
        super(context);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();

        @SuppressLint("DrawAllocation") Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tree);

        @SuppressLint("DrawAllocation") Matrix matrix = new Matrix();
        matrix.setTranslate(100,100);
////        matrix.preTranslate(100,100);
//        matrix.postTranslate(100,100);

        canvas.drawBitmap(bitmap,matrix,paint);

    }
}
