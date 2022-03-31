package com.example.bubuk.semestralniprace.Activity.Examples.PowerLine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.bubuk.semestralniprace.R;


public class ReflectionMenu extends AppCompatActivity implements View.OnClickListener {

    Button sim,theory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflection_menu);
        initcontrol();
    }

    public void initcontrol(){
        sim = (Button) findViewById(R.id.reflectionSimBTN);
        theory = (Button) findViewById(R.id.reflectionTheoryBTN);
        sim.setOnClickListener(this);
        theory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.reflectionSimBTN:
                Intent intent = new Intent(ReflectionMenu.this, Reflection.class);
                startActivity(intent);
                break;

            case R.id.reflectionTheoryBTN:
                Intent intent1 = new Intent(ReflectionMenu.this, ReflectionTheory.class);
                startActivity(intent1);
                break;
        }
    }
}
