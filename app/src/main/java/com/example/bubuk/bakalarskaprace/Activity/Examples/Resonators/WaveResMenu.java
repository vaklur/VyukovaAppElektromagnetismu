package com.example.bubuk.semestralniprace.Activity.Examples.Resonators;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Cavity.WaveResCavity;
import com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Planar.WaveResPlanar;
import com.example.bubuk.semestralniprace.R;

public class WaveResMenu extends AppCompatActivity implements View.OnClickListener{

    Button waverestheory,waverescavity,waveresdiel,waverescoax,waveresplate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_menu);

        initcontrol();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.waveresTheoryBTN:
                Intent intent = new Intent(WaveResMenu.this, WaveResTheory.class);
                startActivity(intent);
                break;

            case R.id.waveresCavityBTN:
                Intent intent1 = new Intent(WaveResMenu.this, WaveResCavity.class);
                startActivity(intent1);
                break;
            case R.id.waveresDielBTN:
                Intent intent2 = new Intent(WaveResMenu.this, WaveResDiel.class);
                startActivity(intent2);
                break;
            case R.id.waveresCoaxBTN:
                Intent intent3 = new Intent(WaveResMenu.this, WaveResCoax.class);
                startActivity(intent3);
                break;
            case R.id.waveresPlanarBTN:
                Intent intent4 = new Intent(WaveResMenu.this, WaveResPlanar.class);
                startActivity(intent4);
                break;
        }
    }

    public void initcontrol(){
        waverestheory= (Button) findViewById(R.id.waveresTheoryBTN);
        waverescavity= (Button) findViewById(R.id.waveresCavityBTN);
        waveresdiel= (Button) findViewById(R.id.waveresDielBTN);
        waverescoax= (Button) findViewById(R.id.waveresCoaxBTN);
        waveresplate= (Button) findViewById(R.id.waveresPlanarBTN);
        waverestheory.setOnClickListener(this);
        waverescavity.setOnClickListener(this);
        waveresdiel.setOnClickListener(this);
        waverescoax.setOnClickListener(this);
        waveresplate.setOnClickListener(this);
    }
}
