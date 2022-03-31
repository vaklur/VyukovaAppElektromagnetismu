package com.example.bubuk.semestralniprace.Activity.Examples.Microstrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.R;

public class MicrostripMenu extends AppCompatActivity implements View.OnClickListener{

    Button analyze, synthese, theory;

    /* create activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microstrip_menu);
        initcontrol();
    }

    /* initialze widgets */
    public void initcontrol(){
        analyze = (Button) findViewById(R.id.microstripAnaBTN);
        analyze.setOnClickListener(this);
        synthese = (Button) findViewById(R.id.microstripSynBTN);
        synthese.setOnClickListener(this);
        theory = (Button) findViewById(R.id.microstripTheoryBTN);
        theory.setOnClickListener(this);

    }

    /* open other activity */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.microstripAnaBTN:
                Intent intent = new Intent(MicrostripMenu.this, MicrostripAnalyze.class);
                startActivity(intent);
                break;
            case R.id.microstripSynBTN:
                Intent intent3 = new Intent(MicrostripMenu.this, MicrostripSynthesis.class);
                startActivity(intent3);
                break;
            case R.id.microstripTheoryBTN:
                Intent intent2 = new Intent(MicrostripMenu.this, MicrostripTheory.class);
                startActivity(intent2);
                break;
        }
    }
}
