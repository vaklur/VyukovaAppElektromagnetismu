package com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Planar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Resonator.Planar.Circle.WaveResPlanarCircleSim;
import com.example.bubuk.semestralniprace.R;


public class WaveResPlanarCircle extends AppCompatActivity {
    WaveResPlanarCircleSim fragmentWRPCircleSim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_planar_circle);
        initcontrol();
        loadFragment(fragmentWRPCircleSim);


    }




    public void initcontrol(){

        fragmentWRPCircleSim = new WaveResPlanarCircleSim();



    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wrPlanCircFL, fragment);
        fragmentTransaction.commit();
    }
}
