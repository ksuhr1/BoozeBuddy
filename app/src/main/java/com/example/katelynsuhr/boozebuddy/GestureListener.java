//package com.example.katelynsuhr.boozebuddy;
//
//import android.content.Context;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ListView;
//import android.widget.Toast;
//
///**
// * Created by katelynsuhr on 12/3/17.
// */
//
//class GestureListener extends GestureDetector.SimpleOnGestureListener
//{
//    private static final int SWIPE_MIN_DISTANCE = 150;
//    private static final int SWIPE_MAX_OFF_PATH = 100;
//    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
//
//    private MotionEvent mLastOnDownEvent = null;
//
//    @Override
//    public boolean onDown(MotionEvent e)
//    {
//        mLastOnDownEvent = e;
//        return super.onDown(e);
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
//    {
//        if(e1 == null){
//            e1 = mLastOnDownEvent;
//        }
//        if(e1==null || e2==null){
//            return false;
//        }
//
//        float dX = e2.getX() - e1.getX();
//        float dY = e1.getY() - e2.getY();
//
//        if (Math.abs(dY) < SWIPE_MAX_OFF_PATH && Math.abs(velocityX) >= SWIPE_THRESHOLD_VELOCITY && Math.abs(dX) >= SWIPE_MIN_DISTANCE ) {
//            if (dX > 0) {
//                Toast.makeText(GestureListener.class, "Right Swipe", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
//        else if (Math.abs(dX) < SWIPE_MAX_OFF_PATH && Math.abs(velocityY)>=SWIPE_THRESHOLD_VELOCITY && Math.abs(dY)>=SWIPE_MIN_DISTANCE ) {
//            if (dY>0) {
//                Toast.makeText(, "Up Swipe", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Down Swipe", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
//        return false;
//    }
//
//
//}
