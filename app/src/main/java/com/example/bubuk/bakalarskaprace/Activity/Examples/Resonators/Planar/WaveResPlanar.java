package com.example.bubuk.semestralniprace.Activity.Examples.Resonators.Planar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bubuk.semestralniprace.R;

public class WaveResPlanar extends AppCompatActivity implements View.OnClickListener {
    Button rectangular, circle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveresonators_planar);
        rectangular = (Button) findViewById(R.id.wrPlanarRectangularBTN);
        circle = (Button) findViewById(R.id.wrPlanarCircleBTN);
        rectangular.setOnClickListener(this);
        circle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.wrPlanarRectangularBTN:
                Intent intent = new Intent(WaveResPlanar.this, WaveResPlanarRectangular.class);
                startActivity(intent);
                break;

            case R.id.wrPlanarCircleBTN:
                Intent intent1 = new Intent(WaveResPlanar.this, WaveResPlanarCircle.class);
                startActivity(intent1);
                break;
        }
    }
}
