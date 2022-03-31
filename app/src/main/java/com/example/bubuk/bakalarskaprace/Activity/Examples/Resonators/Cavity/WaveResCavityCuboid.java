package com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Cavity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Resonator.Cavity.Cuboid.WaveResCavityCuboidSim;
import com.example.bubuk.semestralniprace.R;


public class WaveResCavityCuboid extends AppCompatActivity{

    WaveResCavityCuboidSim fragmentWRCCuboidSim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_cavity_cuboid);
        initcontrol();
        loadFragment(fragmentWRCCuboidSim);


    }




    public void initcontrol(){

        fragmentWRCCuboidSim = new WaveResCavityCuboidSim();

    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wrCavCubFL, fragment);
        fragmentTransaction.commit();
    }
}
