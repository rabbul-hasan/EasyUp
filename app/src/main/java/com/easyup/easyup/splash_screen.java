package com.easyup.easyup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {
    private String IMEINumber;

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_splash_screen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        final int reqcode = 1;

        final Context c = this.getApplicationContext();


        mContentView = findViewById(R.id.fullscreen_content);


        Thread thread = new Thread() {

            public void run() {
                try {
                    sleep(3 * 1000);




                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String[] per = new String[]{Manifest.permission.READ_PHONE_STATE};
                        requestPermissions(per, 1);

                    }


                } catch (Exception e) {
                }


            }


        };


//thread.start();

        thread.start();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("perrr", "granted");
                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {


                    finish();
                    System.exit(1);

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }






    }
}