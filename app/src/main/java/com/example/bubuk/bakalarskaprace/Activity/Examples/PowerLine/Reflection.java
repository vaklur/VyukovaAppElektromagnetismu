package com.example.bubuk.semestralniprace.Activity.Examples.PowerLine;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.bubuk.semestralniprace.Fragments.Reflection.CurrentSW;
import com.example.bubuk.semestralniprace.Fragments.Reflection.ReflectionCoef;
import com.example.bubuk.semestralniprace.Fragments.Reflection.ReflectionSet;
import com.example.bubuk.semestralniprace.Fragments.Reflection.SWR;
import com.example.bubuk.semestralniprace.Fragments.Reflection.VoltageSW;
import com.example.bubuk.semestralniprace.R;


/* Activity, than visualised values on transmission line */
public class Reflection extends AppCompatActivity {

    /*define buttons and fragments*/
    ReflectionCoef fragmentRC;
    SWR fragmentSWR;
    CurrentSW fragmentCSW;
    VoltageSW fragmentVSW;
    ReflectionSet fragmentSet;


    /* Create Activity and load fragments*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflection_sim);

        initcontrol();

        Spinner refspinner = (Spinner) findViewById(R.id.reflectionS);

        String[] plants = new String[]{"Činitel odrazu na vedení","Poměr stojatých vln","Stojaté vlnění proudu","Stojaté vlnění napětí","Nastavení parametrů"
        };

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        refspinner.setAdapter(spinnerArrayAdapter);

        refspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextSize(20);
                ((TextView) parent.getChildAt(0)).setTypeface(null, Typeface.BOLD);

                if (position==0){
                    loadFragment(fragmentRC);
                }
                if (position==1){
                    loadFragment(fragmentSWR);
                }
                if (position==2){
                    loadFragment(fragmentCSW);
                }
                if (position==3){
                    loadFragment(fragmentVSW);
                }
                if (position==4){
                    loadFragment(fragmentSet);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
    }
    /*manage fragments*/
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.reflectionFL, fragment);
        fragmentTransaction.commit();
    }

    public void initcontrol(){
        fragmentRC = new ReflectionCoef();
        fragmentSWR = new SWR();
        fragmentCSW = new CurrentSW();
        fragmentVSW = new VoltageSW();
        fragmentSet = new ReflectionSet();
    }
}









