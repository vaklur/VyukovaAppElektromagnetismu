package com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Cavity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Resonator.Cavity.Cylinder.WaveResCavityCylinderSim;
import com.example.bubuk.semestralniprace.R;


public class WaveResCavityCylinder extends AppCompatActivity {
    WaveResCavityCylinderSim fragmentWRCCylinderSim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_cavity_cylinder);
        initcontrol();
        loadFragment(fragmentWRCCylinderSim);
    }

    public void initcontrol(){

        fragmentWRCCylinderSim = new WaveResCavityCylinderSim();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wrCavCylFL, fragment);
        fragmentTransaction.commit();
    }
}
