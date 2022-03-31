package com.example.bubuk.semestralniprace.Activity.Examples.Waveguide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Waveguide.Coaxial.WaveguideCoaxSim;
import com.example.bubuk.semestralniprace.R;

public class WaveguideCoax extends AppCompatActivity {
    WaveguideCoaxSim fragmentCoaxsim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveguide_coaxial);
        initcontrol();
        loadFragment(fragmentCoaxsim);


    }




    public void initcontrol(){

        fragmentCoaxsim = new WaveguideCoaxSim();



    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wgCoaxialFL, fragment);
        fragmentTransaction.commit();
    }
}

