package com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Planar;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Resonator.Planar.Rectangular.WaveResPlanarRectangularSim;
import com.example.bubuk.semestralniprace.R;

public class WaveResPlanarRectangular extends AppCompatActivity {
    WaveResPlanarRectangularSim fragmentWRPRectangularSim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_planar_rectangular);
        initcontrol();
        loadFragment(fragmentWRPRectangularSim);


    }




    public void initcontrol(){

        fragmentWRPRectangularSim = new WaveResPlanarRectangularSim();



    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wrPlanRectFL, fragment);
        fragmentTransaction.commit();
    }
}
