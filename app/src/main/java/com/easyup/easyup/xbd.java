package com.easyup.easyup;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class xbd extends AsyncTask<String, String,String> {
 // String URL = "http://10.0.2.2/app1.php";
TextView textView,textView1,impression, tk;
String URL="https://cavid19.000webhostapp.com/app1.php";

Context context;

  String ss, imei,phone;
    public xbd(TextView textView, TextView textView1, TextView impression, TextView tk, String imei, String phone) {
this.imei=imei;

this.phone=phone;
        this.context=context;

this.textView=textView;
this.textView1=textView1;
this.tk=tk;
this.impression=impression;


    }

    @Override

    protected void onPreExecute() {

        super.onPreExecute();

    }

    @Override

    protected String doInBackground(String... args) {





        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();



        urlParameters.add(new BasicNameValuePair("imei",this.imei));

        urlParameters.add(new BasicNameValuePair("phone",this.phone));


        HttpClientExample htp = new HttpClientExample(urlParameters);
        try {
            ss = htp.sendPost(  URL );
        } catch (Exception e) {

            Log.d("myres", e.getMessage());

        }






        return ss.toString();

    }

    @Override
    protected void onPostExecute(String result) {
Log.d("l1",ss);
       String strArray[] = ss.split("-");

textView.setText(String.valueOf(strArray[0]));

textView1.setText("Total click: "+strArray[1]);
tk.setText("");
impression.setText("Total Impression:"+strArray[2]);



    }


}