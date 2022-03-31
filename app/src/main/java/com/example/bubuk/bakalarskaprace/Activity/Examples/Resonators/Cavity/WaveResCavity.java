package com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Cavity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.R;

public class WaveResCavity extends AppCompatActivity implements View.OnClickListener {

    Button cuboid, cylinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_cavity);

        initcontrol();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.wrCavCuboidBTN:
                Intent intent = new Intent(WaveResCavity.this, WaveResCavityCuboid.class);
                startActivity(intent);
                break;

            case R.id.wrCavCylinderBTN:
                Intent intent1 = new Intent(WaveResCavity.this, WaveResCavityCylinder.class);
                startActivity(intent1);
                break;
        }
    }

    public void initcontrol(){
        cuboid = (Button) findViewById(R.id.wrCavCuboidBTN);
        cylinder = (Button) findViewById(R.id.wrCavCylinderBTN);
        cuboid.setOnClickListener(this);
        cylinder.setOnClickListener(this);
    }
}
