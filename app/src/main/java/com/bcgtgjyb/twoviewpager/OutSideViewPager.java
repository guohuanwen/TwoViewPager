package com.bcgtgjyb.twoviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by bigwen on 2015/12/1.
 */
public class OutSideViewPager extends ViewPager {
    private String TAG=OutSideViewPager.class.getName();
    private ViewPager childeViewPager;
    private float startTouch;
    public OutSideViewPager(Context context) {
        this(context, null);
    }

    public OutSideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setViewPager(ViewPager viewPager){
        this.childeViewPager=viewPager;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent ");
        float x=ev.getX();
        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                startTouch=x;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx=x-startTouch;
                //子pagerView没有滑倒最后一页，不拦截
                if((childeViewPager.getCurrentItem()+1)!=childeViewPager.getAdapter().getCount()){
                    return false;
                }else {
                    //子pagerView滑倒最后一页，但是此时向右滑，不拦截
                    if(dx>0){
                        return false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent ");
        return super.onTouchEvent(ev);
    }
}
