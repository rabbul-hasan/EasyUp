package com.easyup.easyup.ui.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.easyup.easyup.R;
import com.easyup.easyup.xbd;
import com.google.android.material.navigation.NavigationView;

import static android.content.Context.TELEPHONY_SERVICE;

public class GalleryFragment extends Fragment {

TextView textView;
    TextView textViewa, textView1,impression, tk,id,random;
    String IMEINumber;
    Button button;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        id=(TextView)root.findViewById(R.id.id);



        random=(TextView)root.findViewById(R.id.random);

        textView1=(TextView)root.findViewById(R.id.txt1);

        tk=(TextView)root.findViewById(R.id.tk);
        impression=(TextView)root.findViewById(R.id.impression);
        long t= System.currentTimeMillis()-150000000;
        String result = String.format("%04d", t % 10000);
        Log.d("mili",result);





        random.setText(result);


        button=(Button)root.findViewById(R.id.button);

        textView=(TextView)root.findViewById(R.id.txt);

        TelephonyManager tm = (TelephonyManager)getActivity().getSystemService(TELEPHONY_SERVICE);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            IMEINumber = tm.getImei();

          //  phone=tm.getLine1Number();
        }else { IMEINumber=tm.getDeviceId();
           // phone=tm.getLine1Number();

        }

        id.setText("ID #"+IMEINumber);










        button.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                //   ne.start();


                xbd xbd = new xbd(textView,textView1,impression, tk,IMEINumber,"a");
                xbd.execute();



            }
        });





        NavigationView navigationView =getActivity(). findViewById(R.id.nav_view);
        View view=  navigationView.getHeaderView(0);
      //  navigationView.getHeaderView(0);
        textView=(TextView)view.findViewById(R.id.textView);
        textView.setText("gggg");
        return root;






    }
}