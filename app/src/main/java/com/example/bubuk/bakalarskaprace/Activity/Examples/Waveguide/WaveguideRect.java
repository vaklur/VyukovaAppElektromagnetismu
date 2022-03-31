package com.example.bubuk.semestralniprace.Activity.Examples.Waveguide;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.Fragments.Waveguide.Rectangular.WaveguideRectSet;
import com.example.bubuk.semestralniprace.Fragments.Waveguide.Rectangular.WaveguideRectSim;
import com.example.bubuk.semestralniprace.OtherClass.SharePref;
import com.example.bubuk.semestralniprace.R;



public class WaveguideRect extends AppCompatActivity {


    WaveguideRectSim fragmentRsim;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveguide_rectangular);
        initcontrol();
        loadFragment(fragmentRsim);


    }




    public void initcontrol(){

        fragmentRsim = new WaveguideRectSim();



    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wgRectangularFL, fragment);
        fragmentTransaction.commit();
    }
}