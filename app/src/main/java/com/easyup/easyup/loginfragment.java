package com.easyup.easyup;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link loginfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link loginfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ImageView imageView;
    EditText emaileditEditText, passowordEditText ;
    Button btnSignIn, btnSignUp;
    String url = "http://10.0.2.2/index.php";





    public loginfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static loginfragment newInstance(String param1, String param2, Context context) {
        loginfragment fragment = new loginfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout3, container, false);
        imageView=(ImageView)view.findViewById(R.id.image);

emaileditEditText=(EditText)view.findViewById(R.id.userEmailId);
passowordEditText=(EditText)view.findViewById(R.id.password);

btnSignUp=(Button)view.findViewById(R.id.sinUp);
        btnSignIn=(Button)view.findViewById(R.id.login);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "ddd",
                        Toast.LENGTH_LONG).show();



                Fragment signupFragment = new sinup();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.account, signupFragment);
                fragmentTransaction.commit();

            }
        });



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ArrayList loginkey=new ArrayList();
                loginkey.add("'password");
                loginkey.add("email");

                ToLogin toLogin=new ToLogin(loginkey, getActivity());
                Log.d("editText", passowordEditText.getText().toString());
             toLogin.execute(passowordEditText.getText().toString(),emaileditEditText.getText().toString(),"requestlogin",url);


            }
        });




                return view;


    }






    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


class ToLogin extends AsyncTask<String, String,String> {
    String loginresponce="aa";
    ArrayList key;
    Context context;

    ToLogin( ArrayList key, Context context ){
        this.key=key;
        this.context=context;

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

int i=0;


        for (String s : key)
        {
          //  urlParameters.add(new BasicNameValuePair(s ,args[0]));
        }

        urlParameters.add(new BasicNameValuePair("password", args[0]));


        urlParameters.add(new BasicNameValuePair("email", args[1]));
        urlParameters.add(new BasicNameValuePair("requestcode", args[2]));


     //   urlParameters.add(new BasicNameValuePair("phone", args[3]));
        //  urlParameters.add(new BasicNameValuePair("username",args[0] ));

       HttpClientExample htp =new HttpClientExample(urlParameters);
        try {
        this.loginresponce=  htp.sendPost(args[args.length-1]);
        }

        catch (Exception e){}


        return loginresponce;

    }
    @Override
    protected void onPostExecute(String result) {

        Log.d("logintext", loginresponce);
        try {
           JSONObject json = new JSONObject(loginresponce.toString());

            Log.d("jsondata", json.getString("message"));

            if(json.getString("success").equals("0")){
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.onlogindialog);
                dialog.setTitle("Title...");
                final TextView text = (TextView) dialog.findViewById(R.id.text);


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                text.setText(json.getString("message"));








            }

             //  json.getString("message");






        } catch (Exception e) {
            e.getMessage();
Log.d("problen",e.getMessage());
        }

    }
}





