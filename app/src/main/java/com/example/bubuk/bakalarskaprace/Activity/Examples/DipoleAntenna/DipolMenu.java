package com.example.bubuk.semestralniprace.Activity.Examples.DipoleAntenna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.R;

public class DipolMenu extends AppCompatActivity implements View.OnClickListener{

    Button Simulation,Theory;

    /* create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipolmenu);
        initcontrol();
    }

    /* initialize widgets*/
    public void initcontrol(){
        Simulation = (Button) findViewById(R.id.dipolSimBTN);
        Simulation.setOnClickListener(this);
        Theory = (Button) findViewById(R.id.dipolTheoryBTN);
        Theory.setOnClickListener(this);

    }

    /* open other activity*/
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dipolSimBTN:
                Intent intent = new Intent(DipolMenu.this, Dipol.class);
                startActivity(intent);
                break;
            case R.id.dipolTheoryBTN:
                Intent intent1 = new Intent(DipolMenu.this, DipolTheory.class);
                startActivity(intent1);
                break;
        }
    }
}