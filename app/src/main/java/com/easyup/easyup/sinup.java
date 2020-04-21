package com.easyup.easyup;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;


public class sinup extends Fragment {
  public   String responce="eeee";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String name,IMEINumber, passwordText, emailadddress;

    String mParam1;
    String mParam2;
    //  private OnFragmentInteractionListener mListener;
    Button btnSignIn, btnSignUp;
    EditText fullname, email, password, e;


    String URL = "http://10.0.2.2/index.php";

  //  String URL="http://192.168.43.115/index.php";
Context context=getContext();

    public sinup() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int i;
        View view = inflater.inflate(R.layout.fragment_sinup, container, false);
        btnSignIn = (Button) view.findViewById(R.id.login);
        btnSignUp = (Button) view.findViewById(R.id.sinUp);

        fullname = (EditText) view.findViewById(R.id.fullName);
     //   phoneNumber = (EditText) view.findViewById(R.id.mobileNumber);
        email = (EditText) view.findViewById(R.id.userEmailId);
        password = (EditText) view.findViewById(R.id.password);
        name = fullname.getText().toString();


        TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(TELEPHONY_SERVICE);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            IMEINumber = tm.getImei();

            //  phone=tm.getLine1Number();
        }else { IMEINumber=tm.getDeviceId();
            // phone=tm.getLine1Number();

        }






        emailadddress = email.getText().toString();
        passwordText = password.getText().toString();


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "ddd",
                        Toast.LENGTH_LONG).show();


                Fragment loginfragment = new loginfragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.account, loginfragment);
                fragmentTransaction.commit();

            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Intent intent = new Intent(getContext(), Verify.class);

                intent.putExtra("fulname",String.valueOf(fullname.getText()));
                intent.putExtra("password", String.valueOf(password.getText()));

                intent.putExtra("email",String.valueOf(email.getText()));

                intent.putExtra("imei",IMEINumber);

                intent.putExtra("url",URL);


               startActivity(intent);







//When you need to access any web page with post method then call like this and get string
        //        responce
                //
                //


Context context=getActivity();














        //    Log.d("ponce",attemptLogin.responce);
            //    ImageV iew image = (ImageView) dialog.findViewById(R.id.image);
              //  image.setImageResource(R.drawable.ic_launcher);








            }


        });

        return view;









    }


    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
















