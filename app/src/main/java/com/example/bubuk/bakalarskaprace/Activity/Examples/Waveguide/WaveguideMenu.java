package com.example.bubuk.semestralniprace.Activity.Examples.Waveguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.Activity.Examples.PlaneWave.PlaneWaveMenu;
import com.example.bubuk.semestralniprace.Activity.Examples.PlaneWave.PlaneWaveSim;
import com.example.bubuk.semestralniprace.Activity.Examples.PlaneWave.PlaneWaveTheory;
import com.example.bubuk.semestralniprace.R;


public class WaveguideMenu extends AppCompatActivity implements View.OnClickListener{

    Button Rect,Circ,Coax,Theory;
    /* Create activity*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveguide_menu);
        initcontrol();
    }

    public void initcontrol(){
        Rect = (Button) findViewById(R.id.waveguideRectBTN);
        Rect.setOnClickListener(this);
        Circ = (Button) findViewById(R.id.waveguideCircBTN);
        Circ.setOnClickListener(this);
        Coax = (Button) findViewById(R.id.waveguideCoaxBTN);
        Coax.setOnClickListener(this);
        Theory = (Button) findViewById(R.id.waveguideTheoryBTN);
        Theory.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.waveguideRectBTN:
                Intent intent = new Intent(WaveguideMenu.this, WaveguideRect.class);
                startActivity(intent);
                break;
            case R.id.waveguideCircBTN:
                Intent intent1 = new Intent(WaveguideMenu.this, WaveguideCirc.class);
                startActivity(intent1);
                break;
            case R.id.waveguideCoaxBTN:
                Intent intent2 = new Intent(WaveguideMenu.this, WaveguideCoax.class);
                startActivity(intent2);
                break;
            case R.id.waveguideTheoryBTN:
                Intent intent3 = new Intent(WaveguideMenu.this, WaveguideTheory.class);
                startActivity(intent3);
                break;
        }
    }
}
