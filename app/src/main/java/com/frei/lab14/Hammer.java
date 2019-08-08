package com.frei.lab14;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

public class Hammer {

    Bitmap bitmap;
    Rect mSrcRect,mDestRect;
    Paint paint;

    public Hammer(Bitmap bitmap, Rect mSrcRect, Rect mDestRect, Paint paint) {
        this.bitmap = bitmap;
        this.mSrcRect = mSrcRect;
        this.mDestRect = mDestRect;
        this.paint = paint;
    }
}
