package com.example.bubuk.semestralniprace.Activity.Examples;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.bubuk.semestralniprace.Activity.Examples.DipoleAntenna.DipolMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.FresnelElipsoid.FresnelElipsoidMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.Microstrip.MicrostripMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.PassiveFilters.PassiveFilter;
import com.example.bubuk.semestralniprace.Activity.Examples.PlaneWave.PlaneWaveMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.PowerLine.ReflectionMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.Resonators.WaveResMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.Waveguide.WaveguideMenu;
import com.example.bubuk.semestralniprace.R;



public class Examples extends AppCompatActivity{



    ListView list;
    String[] itemname ={
            "Rovinná vlna",
            "Odrazy na vedení",
            "Fresnelova zóna",
            "Vyzařování dipólu",
            "Návrh Mikropásku",
            "Mikrovlnné vlnovody",
            "Mikrovlnné rezonátory",
            "Pasivní filtry"
    };

    Integer[] imgid={
            R.drawable.planewave,
            R.drawable.reflection,
            R.drawable.fresnel,
            R.drawable.dipol,
            R.drawable.microstrip1,
            R.drawable.wg,
            R.drawable.resonancecurve,
            R.drawable.passivefilter,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examples);

        ExamplesAdapter adapter=new ExamplesAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.examplesLV);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if(position==0){
                    Intent intent = new Intent(Examples.this, PlaneWaveMenu.class);
                    startActivity(intent);}
                if(position==1){
                    Intent intent = new Intent(Examples.this, ReflectionMenu.class);
                    startActivity(intent);}
                if(position==2){
                    Intent intent = new Intent(Examples.this, FresnelElipsoidMenu.class);
                    startActivity(intent);}
                if(position==3){
                    Intent intent = new Intent(Examples.this, DipolMenu.class);
                    startActivity(intent);}
                if(position==4){
                    Intent intent = new Intent(Examples.this, MicrostripMenu.class);
                    startActivity(intent);}
                if(position==5){
                    Intent intent = new Intent(Examples.this, WaveguideMenu.class);
                    startActivity(intent);}
                if(position==6){
                    Intent intent = new Intent(Examples.this, WaveResMenu.class);
                    startActivity(intent);}
                if(position==7){
                    Intent intent = new Intent(Examples.this, PassiveFilter.class);
                    startActivity(intent);}
            }
        });
    }
}
