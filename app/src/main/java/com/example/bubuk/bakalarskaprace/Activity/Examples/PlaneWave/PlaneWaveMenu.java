package com.example.bubuk.semestralniprace.Activity.Examples.PlaneWave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.R;

public class PlaneWaveMenu extends AppCompatActivity implements View.OnClickListener{

    Button Simulation,Theory;

    /* create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plane_wave_menu);
        initcontrol();
    }

    /* initialize widgets*/
    public void initcontrol(){
        Simulation = (Button) findViewById(R.id.planeWaveSimBTN);
        Simulation.setOnClickListener(this);
        Theory = (Button) findViewById(R.id.planeWaveTheoryBTN);
        Theory.setOnClickListener(this);

    }

    /* open other activity*/
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.planeWaveSimBTN:
                Intent intent = new Intent(PlaneWaveMenu.this, PlaneWaveSim.class);
                startActivity(intent);
                break;
            case R.id.planeWaveTheoryBTN:
                Intent intent1 = new Intent(PlaneWaveMenu.this, PlaneWaveTheory.class);
                startActivity(intent1);
                break;
        }
    }
}
