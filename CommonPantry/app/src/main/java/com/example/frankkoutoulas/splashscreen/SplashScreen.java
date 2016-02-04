package com.example.frankkoutoulas.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class SplashScreen extends Activity {

    /**
     * call SplashScreen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        /**
         * timerThread
         * sleep used for delay
         */
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        }
    };
    timerThread.start();


}

    @Override
    protected void onPause() {
    super.onPause();
        finish();

    }
}
