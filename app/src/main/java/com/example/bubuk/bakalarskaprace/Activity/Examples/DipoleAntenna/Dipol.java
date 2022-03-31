package com.example.bubuk.semestralniprace.Activity.Examples.DipoleAntenna;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.Fragments.Dipol.DipolEKartez;
import com.example.bubuk.semestralniprace.Fragments.Dipol.DipolEPolar;
import com.example.bubuk.semestralniprace.Fragments.Dipol.DipolHKartez;
import com.example.bubuk.semestralniprace.Fragments.Dipol.DipolHPolar;
import com.example.bubuk.semestralniprace.Fragments.Dipol.DipolSet;
import com.example.bubuk.semestralniprace.R;

public class Dipol extends AppCompatActivity {

    /*define fragments*/
    DipolEKartez fragmentEK;
    DipolEPolar fragmentEP;
    DipolHKartez fragmentHK;
    DipolHPolar fragmentHP;
    DipolSet fragmentSet;

    /*create activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipol);

        fragmentEK = new DipolEKartez();
        fragmentEP = new DipolEPolar();
        fragmentHK = new DipolHKartez();
        fragmentHP = new DipolHPolar();
        fragmentSet = new DipolSet();


        Spinner dipolspinner = (Spinner) findViewById(R.id.dipolS);

        String[] dipolspinnerdata = new String[]{"Rovina E kartézské souřadnice","Rovina H kartézské souřadnice","Rovina E polární souřadnice","Rovina H polární souřadnice","Parametry dipólu"};

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dipolspinnerdata);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dipolspinner.setAdapter(spinnerArrayAdapter);
        /* load fragments with radiating directional characteristic of dipole and dipole set*/
        dipolspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextSize(20);
                ((TextView) parent.getChildAt(0)).setTypeface(null, Typeface.BOLD);

                if (position==0){
                    loadFragment(fragmentEK);
                }
                if (position==1){
                    loadFragment(fragmentHK);
                }
                if (position==2){
                    loadFragment(fragmentEP);
                }
                if (position==3){
                    loadFragment(fragmentHP);
                }
                if (position==4){
                    loadFragment(fragmentSet);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing...
            }
        });

    }

    /*manage fragments*/
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.dipolFL, fragment);
        fragmentTransaction.commit();
    }

}




