package com.example.cxhll.huifenq.tools;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by CXHLL on 2016/12/20.
 */

public class MyLisrView extends ListView {
    //禁止侧滑
    public static int MOD_FORBID=0;
    //右滑菜单
    public  static int MOD_LEFT=1;
    //左滑菜单
    public  static int MOD_RIGHT=2;
    //两边菜单
    public  static int MOD_BOTH=3;
    //当前模式
    private   int mode=MOD_FORBID;
    //左侧菜单的长度
    private int leftLength=0;
    //右滑菜单的长度
    private int rightLength=0;
    //当前滑动的ListView position
    private  int slidePosition;
    //手指按下的x坐标
    private  int downY;
    //手指按下的Y坐标
    private  int downX;

    //Listview的ITEM
    private View itemView;

    //滑动类
    private Scroller scroller;
    //认为是用户滑动的最小距离
    private int mTouchSlop;
    //判断是否可以侧向滑动
    private  boolean canMove=false;
    //标志是否完成侧滑
    private boolean isSlided=false;

    public MyLisrView(Context context) {
        super(context,null);
    }

    public MyLisrView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyLisrView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        scroller=new Scroller(context);
        mTouchSlop= ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }
    //初始化菜单滑出模式
    public void initSlideMode(int mode){
        this.mode=mode;

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final  int action=ev.getAction();
        int lastX= (int) ev.getX();

        switch (action){
            case MotionEvent.ACTION_DOWN:
               System.out.println("touch-->"+"down");
                //如果当前模式不允许滑动，则直接返回
                if (this.mode==MOD_FORBID){

                    return  super.onTouchEvent(ev);
                }
                //如果处于侧滑完成状态，侧滑回去，并且直接返回
                if (isSlided){
                    scrollBack();
                    return false;
                }
                //如果滑动还没有结束，直接返回
                if (!scroller.isFinished()){
                    return false;
                }
                downX= (int) ev.getX();
                downY= (int) ev.getY();

                slidePosition=pointToPosition(downX,downY);

                //无效的position，不做任何操作
                if (slidePosition== AdapterView.INVALID_POSITION){
                    return super.onTouchEvent(ev);
                }
                //获取点击的item view
                itemView=getChildAt(slidePosition-getFirstVisiblePosition());

                //此处根据设定的滑动模式，自动获取左侧或右侧菜单的长度
                if (this.mode==MOD_BOTH){
                    this.leftLength=-itemView.getPaddingLeft();
                    this.rightLength=-itemView.getPaddingRight();

                }else if(this.mode==MOD_LEFT){
                    this.leftLength=-itemView.getPaddingLeft();
                }else if(this.mode==MOD_RIGHT){
                    this.rightLength=-itemView.getPaddingRight();

                }
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("touch-->"+"move");
                if (!canMove
                        && slidePosition !=AdapterView.INVALID_POSITION
                        &&(Math.abs(ev.getX()-downX)>mTouchSlop &&Math.abs(ev.getY()-downY)<mTouchSlop)){
                    int offsetX=downX-lastX;
                    if (offsetX>0 &&(this.mode==MOD_BOTH||this.mode==MOD_RIGHT)){
                        //从右向左滑
                        canMove=true;
                    }else if(offsetX<0 &&(this.mode==MOD_BOTH||this.mode==MOD_LEFT)) {
                        //从左向右滑
                        canMove=true;
                    }else {
                        canMove=false;
                    }
                    MotionEvent cancelEvent=MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL|(ev.getActionIndex()<<MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    onTouchEvent(cancelEvent);
                }
                if (canMove){
                    //设置此属性，可以在侧向滑动时，保持ListView不会上下滚动
                    requestDisallowInterceptTouchEvent(true);

                    //手指拖动itemView滚动，deltalX大于0向左滚动
                    int deltaX=downX-lastX;
                    if (deltaX<0&&this.mode==MOD_BOTH||this.mode==MOD_LEFT){
                        itemView.scrollTo(deltaX,0);
                    }else  if (deltaX>0&&this.mode==MOD_BOTH||this.mode==MOD_RIGHT){
                        itemView.scrollTo(deltaX,0);
                    }else {
                        itemView.scrollTo(0,0);
                    }

                    return true;
                }
            case MotionEvent.ACTION_UP:
                System.out.println("touch-->"+"up");
                if (canMove){
                    canMove=false;
                    scrollByDistanceX();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }
    private void scrollByDistanceX() {
        if (this.mode == MOD_FORBID) {
            return;
        }
        if (itemView.getScrollX()>0&&(this.mode==MOD_BOTH||this.mode==MOD_RIGHT)){
            //向左滑
            if (itemView.getScrollX() >= rightLength / 2) {
                scrollLeft();
            } else {
                scrollBack();
            }
        }else if (itemView.getScrollX()<0&&(this.mode==MOD_BOTH||this.mode==MOD_LEFT)){
            if (itemView.getScrollX() <= -rightLength / 2) {
                scrollRight();
            } else {
                scrollBack();
            }
        }else {
            scrollBack();
        }
    }

    //向右滑动，getScrollX()返回的是左边缘的距离
    private  void scrollRight(){
        isSlided=true;
        final  int delta=(leftLength+itemView.getScrollX());
        //调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        scroller.startScroll(itemView.getScrollX(),0,-delta,0,Math.abs(delta));
        postInvalidate();//刷新

    }
    //
    private  void scrollLeft(){
        isSlided=true;
        final  int delta=(rightLength-itemView.getScrollX());
        //调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        scroller.startScroll(itemView.getScrollX(),0,delta,0,Math.abs(delta));
        postInvalidate();//刷新

    }
//滑动到原来的位置
    private  void scrollBack(){
        isSlided=false;
        scroller.startScroll(itemView.getScrollX(),0,-itemView.getScrollX(),0,Math.abs(itemView.getScrollX()));
        postInvalidate();//刷新

    }

    @Override
    public void computeScroll() {
        //调用startScroll的时候scroller.computeScrollOffset()返回true
        if (scroller.computeScrollOffset()){
            //让ListView item 根据当前的滚动偏移量进行滚动
            itemView.scrollTo(scroller.getCurrX(),scroller.getCurrY());
        postInvalidate();
        }

    }

    //外部调用，用于将侧滑出的划回去
    public void sLideBack(){
        this.scrollBack();
    }
}
