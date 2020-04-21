package com.easyup.easyup;

import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;


import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ViewAnimator;


 public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  loginFragment();



        Fragment loinfrogment = new loginfragment();
        FragmentManager fm = getSupportFragmentManager();FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.account, loinfrogment);
        fragmentTransaction.commit();
        Log.d("d1","d1");





        //   showSignupFragment();
    }
    private void showSignupFragment(){
        Fragment signupFragment = new sinup();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        //   transaction.addToBackStack(null);
        transaction.replace(R.id.account, signupFragment);
        transaction.commit();
    }

    private void loginFragment(){
        Fragment signupFragment = new loginfragment();
        //  FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        //   transaction.addToBackStack(null);
        //  transaction.replace(R.id.account, signupFragment);
        // transaction.commit();
    }




}

