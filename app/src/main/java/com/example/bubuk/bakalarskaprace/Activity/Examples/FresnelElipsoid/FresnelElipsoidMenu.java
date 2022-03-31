package com.example.bubuk.semestralniprace.Activity.Examples.FresnelElipsoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.R;

public class FresnelElipsoidMenu extends AppCompatActivity implements View.OnClickListener{

    Button Simulation,Theory;

    /* create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresnel_elipsoid_menu);
        initcontrol();
    }

    /* initialize widgets*/
    public void initcontrol(){
        Simulation = (Button) findViewById(R.id.fresnelSimBTN);
        Simulation.setOnClickListener(this);
        Theory = (Button) findViewById(R.id.fresnelTheoryBTN);
        Theory.setOnClickListener(this);
    }

    /* open other activity*/
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fresnelSimBTN:
                Intent intent = new Intent(FresnelElipsoidMenu.this, FirstFresnelElipsoid.class);
                startActivity(intent);
                break;
            case R.id.fresnelTheoryBTN:
                Intent intent1 = new Intent(FresnelElipsoidMenu.this, FresnelElipsoidTheory.class);
                startActivity(intent1);
                break;
        }
    }
}
