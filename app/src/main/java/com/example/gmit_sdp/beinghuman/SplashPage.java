package com.example.gmit_sdp.beinghuman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_page);
        getSupportActionBar().hide();

        LogoLauncher logolauncher=new LogoLauncher();
        logolauncher.start();
    }

    private class LogoLauncher extends Thread {
        public void run()
        {
            try{
                sleep(4000);
            }
            catch (Exception e){}
            Intent i=new Intent(SplashPage.this,Login.class);
            startActivity(i);
            SplashPage.this.finish();
        }

    }
        /*setContentView(R.layout.activity_splash_page);
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep( 1000);

                } catch (Exception e) {

                }
                finally {
                    Intent i=new Intent(getApplicationContext(),HomePage.class);
                    startActivity(i);
                }
            }
        };*/






    /*protected void  goto4(View v)
    {

    }*/
}
