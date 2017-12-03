//package com.example.katelynsuhr.boozebuddy;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
///**
// * Created by katelynsuhr on 11/29/17.
// */
//
//public class SplashScreen extends Activity {
////        protected void onCreate(Bundle savedInstanceState) {
////            super.onCreate(savedInstanceState);
////            setContentView(R.layout.splash_screen);
////            // Start home activity
////            startActivity(new Intent(SplashScreen.this, IntroMenu.class));
////            // close splash activity
////            finish();
////        }
//    /** Duration of wait **/
//    private final int SPLASH_DISPLAY_LENGTH = 4000;
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        setContentView(R.layout.splash_screen);
//
//        /* New Handler to start the Menu-Activity
//         * and close this Splash-Screen after some seconds.*/
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(SplashScreen.this, IntroMenu.class);
//                startActivity(mainIntent);
//                finish();
//               // SplashScreen.this.startActivity(mainIntent);
//               // SplashScreen.this.finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);
//    }
//}
