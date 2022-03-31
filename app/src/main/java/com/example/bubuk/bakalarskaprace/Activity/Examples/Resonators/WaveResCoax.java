package com.example.bubuk.semestralniprace.Activity.Examples.Resonators;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bubuk.semestralniprace.Fragments.Resonator.Coax.WaveResCoaxSim;
import com.example.bubuk.semestralniprace.R;

public class WaveResCoax extends AppCompatActivity {
    WaveResCoaxSim fragmentWRCoaxSim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_coax);
        initcontrol();
        loadFragment(fragmentWRCoaxSim);
    }




    public void initcontrol(){
        fragmentWRCoaxSim = new WaveResCoaxSim();
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.wrCoaxFL, fragment);
        fragmentTransaction.commit();
    }
}
