package com.easyup.easyup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
public class Verify extends AppCompatActivity {

    EditText editTextCountryCode, editTextPhone;
    AppCompatButton buttonContinue;
 String name,password,email,imei,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifycode);
        editTextCountryCode = findViewById(R.id.editTextCountryCode);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonContinue = findViewById(R.id.buttonContinue);


        Bundle intent1 = getIntent().getExtras();

        
        name = intent1.getString("fulname");
         password = intent1.getString("password");
         email=intent1.getString("email");
         imei=intent1.getString("imei");
         url=intent1.getString("url");



        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCountryCode.getText().toString().trim();
                String number = editTextPhone.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editTextPhone.setError("Valid number is required");
                    editTextPhone.requestFocus();
                    return;
                }

                String phoneNumber = code + number;




                Intent intent = new Intent(Verify.this, verifycode.class);
                intent.putExtra("fulname", name);
                intent.putExtra("password", password);
                intent.putExtra("email", email);
                intent.putExtra("imei", imei);
                intent.putExtra("url", url);
                intent.putExtra("phone", phoneNumber);






                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
