package com.example.bubuk.semestralniprace.Activity.Examples.Resonators;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Resonator.Diel.WaveResDielSim;
import com.example.bubuk.semestralniprace.R;

public class WaveResDiel extends AppCompatActivity {
    WaveResDielSim fragmentWRDielSim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_diel);
        initcontrol();
        loadFragment(fragmentWRDielSim);
    }

    public void initcontrol(){
        fragmentWRDielSim = new WaveResDielSim();
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wrDielFL, fragment);
        fragmentTransaction.commit();
    }
}
