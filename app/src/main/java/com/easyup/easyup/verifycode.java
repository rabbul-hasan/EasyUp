package com.easyup.easyup;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class verifycode extends AppCompatActivity {

    private String verificationId;
    private FirebaseAuth mAuth;

    ProgressBar progressBar;
    EditText editText;
    Button buttonSignIn;
public  String name,password,email,imei,url,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);








        ArrayList key =new ArrayList();


        key.add("username");

        key.add("password");
        key.add("email");
        key.add("imei");


        Intent intent = getIntent();
        name = intent.getStringExtra("fulname");
        password = intent.getStringExtra("password");
        email=intent.getStringExtra("email");
        imei=intent.getStringExtra("imei");
        url=intent.getStringExtra("url");
        phone=intent.getStringExtra("phone");






        Toregistration attemptLogin = new Toregistration(key, getApplicationContext());
        attemptLogin.execute(name, password, email, imei, url);



        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        editText = findViewById(R.id.editTextCode);
        buttonSignIn = findViewById(R.id.buttonSignIn);

       String phone = getIntent().getStringExtra("phone");
     //  String phoone="+8801915090046";
        sendVerificationCode(phone);

        // save phone number
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("USER_PREF",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("phone", phone);
        editor.apply();




        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {










                String code = editText.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {





                         // Intent intent1 = new Intent(verifycode.this, Home.class);
                         //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                          //  startActivity(intent1);

                        } else {
                            Toast.makeText(verifycode.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );

        progressBar.setVisibility(View.GONE);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            Toast.makeText(verifycode.this, "send code", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
            //    editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(verifycode.this, e.getMessage(), Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    };
}




class Toregistration extends AsyncTask<String, String,String> {
    String ss="dd";
    ArrayList key;
    Context context;
    public    String responce="kk";

    TextView textView;
    Toregistration(ArrayList key ,Context context){
        this.context=context;
        this.textView=textView;
        this.key=key;
    }
    @Override

    protected void onPreExecute() {

        super.onPreExecute();

    }

    @Override

    protected String doInBackground(String... args) {

        int lenth =args.length;

        ArrayList< String > key =this.key;

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();


        for (String s : key)
        {
            urlParameters.add(new BasicNameValuePair(s ,args[0]));
        }

        urlParameters.add(new BasicNameValuePair("password", args[1]));


        urlParameters.add(new BasicNameValuePair("email", args[2]));

        urlParameters.add(new BasicNameValuePair("imei", args[3]));

        //  urlParameters.add(new BasicNameValuePair("username",args[0] ));


        HttpClientExample htp =new HttpClientExample(urlParameters);
        try {
            ss=   htp.sendPost(args[args.length-1]);
        }

        catch (Exception e){}


        //    Toast.makeText(getActivity(),ss.toString(),
        //    Toast.LENGTH_LONG).show();



        Log.d("myres", args[1]);



        return ss.toString();

    }
    @Override
    protected void onPostExecute(String result)
    {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.onsinupdialog);
        dialog.setTitle("Title...");
        TextView text = (TextView) dialog.findViewById(R.id.text);
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
          text.setText("sss");
        dialog.show();

        //   JSONObject jsonObject=ss.



    }


}






