package com.frei.lab14;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    List<Hammer> hammerList = new ArrayList<Hammer>();

    String TAG = "Circle";

    SurfaceHolder mSurfaceHolder; //容器

    Canvas mCanvas; //畫布

    boolean mIsDrawing; //次執行序 執行

    int x,y;

    private Rect mSrcRect, mDestRect;
    private Resources mResources;
    private Bitmap bitmap;

    private Paint paint;

    /**
     * 初始化View
     */
    private void initView(){
        mSurfaceHolder = getHolder();
        //注册回调方法
        mSurfaceHolder.addCallback(this);
        //设置一些参数方便后面绘图
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }

    public MySurface(Context context) {
        super(context);
    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mResources = getResources();
        initView();
        bitmap = BitmapFactory.decodeResource(mResources,R.drawable.head);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mIsDrawing = true;
        //開一個執行緒
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        mIsDrawing = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                x = (int)event.getX();
                y = (int)event.getY();
                mSrcRect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
                mDestRect = new Rect(x-bitmap.getWidth()/2,y-bitmap.getHeight()/2,x+bitmap.getWidth(),y+bitmap.getHeight());
                hammerList.add(new Hammer(bitmap,mSrcRect,mDestRect,paint));

                break;

                case MotionEvent.ACTION_MOVE:

                    break;

                case MotionEvent.ACTION_UP:
                    draw();
                    break;
        }
        return true;
    }

    @Override
    public void run() {

        while (mIsDrawing){



        }
    }


    void draw(){

        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            //绘制背景
            mCanvas.drawColor(Color.WHITE);
            //绘图

            //mCanvas.drawOval(10,10,x,y,paint);
            //mCanvas.drawBitmap(bitmap,mSrcRect,mDestRect,paint);

            Iterator<Hammer> it = hammerList.iterator();
            while (it.hasNext()){
                Hammer h = it.next();
                mCanvas.drawBitmap(h.bitmap,h.mSrcRect,h.mDestRect,h.paint);
            }


        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

}
