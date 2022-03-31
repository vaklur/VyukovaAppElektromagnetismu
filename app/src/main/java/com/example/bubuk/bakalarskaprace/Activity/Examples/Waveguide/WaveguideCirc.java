package com.example.bubuk.semestralniprace.Activity.Examples.Waveguide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Waveguide.Circular.WaveguideCircSim;
import com.example.bubuk.semestralniprace.R;

public class WaveguideCirc extends AppCompatActivity {

    WaveguideCircSim fragmentCsim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveguide_circular);
        initcontrol();
        loadFragment(fragmentCsim);


    }




    public void initcontrol(){

        fragmentCsim = new WaveguideCircSim();



    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wgCircularFL, fragment);
        fragmentTransaction.commit();
    }

}
